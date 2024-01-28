package com.numble.booking.book.service;

import com.numble.booking.book.value.BookingFirstDto;
import com.numble.booking.book.value.BookingSecondDto;
import com.numble.booking.delivery.domain.Delivery;
import com.numble.booking.order.domain.Order;
import com.numble.booking.order.exception.BadRequestOrderException;
import com.numble.booking.delivery.repository.DeliveryRepository;
import com.numble.booking.order.repository.OrderRepository;
import com.numble.booking.performance.domain.Performance;
import com.numble.booking.performance.domain.PerformanceSeat;
import com.numble.booking.performance.exception.NotFoundPerformanceException;
import com.numble.booking.performance.exception.NotFoundPerformanceSeatException;
import com.numble.booking.performance.repository.PerformanceRepository;
import com.numble.booking.performance.repository.PerformanceSeatQuerydslRepository;
import com.numble.booking.performance.repository.PerformanceSeatRepository;
import com.numble.booking.price.domain.PricePolicy;
import com.numble.booking.seat.type.SeatStatus;
import com.numble.booking.seat.value.SeatBookingDto;
import com.numble.booking.seat.value.SeatListVo;
import com.numble.booking.ticket.domain.Ticket;
import com.numble.booking.ticket.repository.TicketRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * Class Name : BookingService
 * Description : 예약 서비스
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
    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

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
    public void bookingSecondStep(BookingSecondDto dto) {
        Performance performance = performanceRepository.findById(dto.getPerformanceId())
                .orElseThrow(NotFoundPerformanceException::new);
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(NotFoundUserException::new);

        // 배송지 저장
        Delivery delivery = MapperUtil.map(dto.getDeliveryDto(), Delivery.class);
        deliveryRepository.save(delivery);

        // 결제 저장
        Order order = Order.create(delivery, user, ReceivingMethod.MOBILE_TICKET);
        orderRepository.save(order);

        // 공연과 사용자 정보로 PENDING 인 좌석 조회하기
        List<PerformanceSeat> pendingSeats = performanceSeatRepository.findByPerformanceAndUser(performance.getId(), user.getId())
                .stream()
                .filter(ps -> SeatStatus.PENDING.equals(ps.getStatus()))
                .collect(Collectors.toList());
        // PerformanceSeat 저장
        for (PerformanceSeat pendingSeat : pendingSeats) {
            pendingSeat.modifyStatus(SeatStatus.OCCUPIED, user);
        }
        performanceSeatRepository.saveAll(pendingSeats);

        // OrderItem 저장, Ticket 생성
        final int ticketKeySize = 10;
        List<Ticket> tickets = new ArrayList<>();
        for (PerformanceSeat pendingSeat : pendingSeats) {
            Integer orgPrice = performance.getPricePolicies().stream()
                    .filter(p -> pendingSeat.getSeatType().equals(p.getSeatType()))
                    .map(PricePolicy::getPrice)
                    .findAny()
                    .orElseThrow(() -> new BadRequestOrderException("존재하지 않는 좌석 유형입니다."));

            // 티켓 생성 Ticket
            String ticketKey = TicketService.getRandomStr(ticketKeySize);
            Ticket.create(order, orgPrice, 1, ticketKey, pendingSeat, user);
        }
        ticketRepository.saveAll(tickets);
    }
}
