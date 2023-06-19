package com.numble.booking.book.service;

import com.numble.booking.book.value.BookingFirstDto;
import com.numble.booking.book.value.BookingSecondDto;
import com.numble.booking.payment.domain.Delivery;
import com.numble.booking.payment.domain.PaymentInfo;
import com.numble.booking.payment.exception.BadRequestPaymentException;
import com.numble.booking.payment.repository.DeliveryRepository;
import com.numble.booking.payment.repository.PaymentInfoRepository;
import com.numble.booking.payment.type.PaymentMethod;
import com.numble.booking.payment.value.PaymentByCardDto;
import com.numble.booking.payment.value.PaymentByEWalletDto;
import com.numble.booking.performance.domain.Performance;
import com.numble.booking.performance.domain.PerformanceSeat;
import com.numble.booking.performance.exception.NotFoundPerformanceException;
import com.numble.booking.performance.exception.NotFoundPerformanceSeatException;
import com.numble.booking.performance.repository.PerformanceRepository;
import com.numble.booking.performance.repository.PerformanceSeatQuerydslRepository;
import com.numble.booking.performance.repository.PerformanceSeatRepository;
import com.numble.booking.seat.type.SeatStatus;
import com.numble.booking.seat.value.SeatBookingDto;
import com.numble.booking.seat.value.SeatListVo;
import com.numble.booking.ticket.domain.Ticket;
import com.numble.booking.ticket.service.TicketService;
import com.numble.booking.ticket.type.ReceivingMethod;
import com.numble.booking.user.domian.User;
import com.numble.booking.user.exception.NotFoundUserException;
import com.numble.booking.user.repository.UserRepository;
import com.numble.booking.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * Class Name : BookingService
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-18	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-18
 */
@Service
@RequiredArgsConstructor
public class BookingService {

    private final PerformanceRepository performanceRepository;
    private final PerformanceSeatRepository performanceSeatRepository;
    private final PerformanceSeatQuerydslRepository performanceSeatQuerydslRepository;
    private final PaymentInfoRepository paymentInfoRepository;
    private final DeliveryRepository deliveryRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long bookingFirstStep(BookingFirstDto dto) {
        Performance performance = performanceRepository.findById(dto.getPerformanceId())
                .orElseThrow(NotFoundPerformanceException::new);

        // 좌석 사용 가능한지 확인
        List<SeatListVo> availableSeats = performanceSeatQuerydslRepository.findAvailableSeats(performance.getId());
        List<Long> seatIds = new ArrayList<>();
        for (SeatBookingDto seat : dto.getSeats()) {
            SeatListVo vo = availableSeats.stream()
                    .filter(as -> as.getSeatId().equals(seat.getSeatId()))
                    .findAny()
                    .orElseThrow(() -> new NotFoundPerformanceSeatException("이미 선택된 좌석입니다."));
            seatIds.add(vo.getSeatId());
        }

        // 좌석 대기 걸기
        pendingSeats(dto, performance, seatIds);

        return performance.getId();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class)
    public void pendingSeats(BookingFirstDto dto, Performance performance, List<Long> seatIds) {
        List<PerformanceSeat> performanceSeats = performanceSeatRepository.findByPerformanceAndBySeats(performance.getId(), seatIds);
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(NotFoundUserException::new);
        for (PerformanceSeat performanceSeat : performanceSeats) {
            performanceSeat.modifyStatus(SeatStatus.PENDING, user);
        }
        performanceSeatRepository.saveAll(performanceSeats);
    }

    @Transactional
    public Long bookingSecondStep(BookingSecondDto dto) {
        Performance performance = performanceRepository.findById(dto.getPerformanceId())
                .orElseThrow(NotFoundPerformanceException::new);
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(NotFoundUserException::new);
        
        // 결제 정보 저장
        PaymentMethod paymentMethod = dto.getPaymentMethod();
        PaymentInfo paymentInfo;
        if (PaymentMethod.CREDIT_CARD.equals(paymentMethod) || PaymentMethod.DEBIT_CARD.equals(paymentMethod)) {
            // 신용카드 || 직불카드
            validateByCard(dto.getCardDto());
            paymentInfo = MapperUtil.map(dto.getCardDto(), PaymentInfo.class);
        } else if (PaymentMethod.E_WALLET.equals(paymentMethod)) {
            // 전자 지갑
            validateByEWallet(dto.getEWalletDto());
            paymentInfo = MapperUtil.map(dto.getEWalletDto(), PaymentInfo.class);
        } else {
            throw new BadRequestPaymentException();
        }
        if (paymentInfo != null) {
            paymentInfoRepository.save(paymentInfo);
        }

        // 배송지 저장
        Delivery delivery = MapperUtil.map(dto.getDeliveryDto(), Delivery.class);
        deliveryRepository.save(delivery);

        // 공연과 사용자 정보로 PENDING 인 좌석 조회하기
        List<PerformanceSeat> pendingSeats = performanceSeatRepository.findByPerformanceAndUser(performance.getId(), user.getId())
                .stream()
                .filter(ps -> SeatStatus.PENDING.equals(ps.getStatus()))
                .collect(Collectors.toList());

        for (PerformanceSeat pendingSeat : pendingSeats) {
            //
        }

        // 티켓 생성
        final int ticketKeySize = 10;
        String ticketKey = TicketService.getRandomStr(ticketKeySize);
        Ticket.create(ticketKey, null, user, ReceivingMethod.POSTAL_MAIL);
        
        return null;
    }

    /**
     * 카드 결제 정보유효성 체크
     */
    private void validateByCard(PaymentByCardDto cardDto) {
        if (cardDto == null) {
            throw new BadRequestPaymentException();
        }
        if (cardDto.getBank() == null) {
            throw new BadRequestPaymentException("은행을 선택해 주세요.");
        }
        if (!StringUtils.hasText(cardDto.getCardNumber())) {
            throw new BadRequestPaymentException("잘못된 카드 번호 형식입니다.");
        }
        if (!StringUtils.hasText(cardDto.getCardExpiration())) {
            throw new BadRequestPaymentException("잘못된 유효기간 형식입니다.");
        }
        if (cardDto.getCardCVV() == null || (cardDto.getCardCVV() < 100 || cardDto.getCardCVV() > 999)) {
            throw new BadRequestPaymentException("잘못된 CVV 입니다.");
        }
    }

    /**
     * 전자 지갑 결제 정보 유효성 체크
     */
    private void validateByEWallet(PaymentByEWalletDto eWalletDto) {
        if (eWalletDto.getEWallet() == null) {
            throw new BadRequestPaymentException("잘못된 페이 결제 방식입니다.");
        }
    }
}
