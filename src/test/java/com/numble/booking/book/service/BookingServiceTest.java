package com.numble.booking.book.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.numble.booking.annotation.ServiceTest;
import com.numble.booking.book.value.BookingFirstDto;
import com.numble.booking.book.value.BookingSecondDto;
import com.numble.booking.payment.repository.DeliveryRepository;
import com.numble.booking.payment.repository.PaymentItemRepository;
import com.numble.booking.payment.repository.PaymentRepository;
import com.numble.booking.payment.type.Bank;
import com.numble.booking.payment.type.PaymentMethod;
import com.numble.booking.payment.value.DeliveryCreateDto;
import com.numble.booking.payment.value.PaymentByCardDto;
import com.numble.booking.performance.domain.Performance;
import com.numble.booking.performance.domain.PerformanceSeat;
import com.numble.booking.performance.repository.PerformanceSeatRepository;
import com.numble.booking.seat.type.SeatStatus;
import com.numble.booking.seat.value.SeatBookingDto;
import com.numble.booking.ticket.domain.Ticket;
import com.numble.booking.ticket.repository.TicketRepository;
import com.numble.booking.user.domian.User;
import org.junit.jupiter.api.Test;

/**
 * <pre>
 * Class Name : BookingServiceTest
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-27 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-06-27
 */
@ServiceTest
class BookingServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private PerformanceSeatRepository performanceSeatRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PaymentItemRepository paymentItemRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Test
    void bookingFirstStep() {
        // given
        Long performanceId = 100001L;
        List<SeatBookingDto> seats = new ArrayList<>();
        seats.add(SeatBookingDto.builder().seatId(100001L).seatNumber("A1").build());
        seats.add(SeatBookingDto.builder().seatId(100002L).seatNumber("A2").build());
        Long userId = 100001L;
        BookingFirstDto dto = new BookingFirstDto();
        dto.setPerformanceId(performanceId);
        dto.setSeats(seats);
        dto.setUserId(userId);

        // then
        Long bookingPerformanceId = bookingService.bookingFirstStep(dto);

        // when
        List<PerformanceSeat> performanceSeats = performanceSeatRepository.findByPerformanceAndUser(bookingPerformanceId, userId);
        assertNotEquals(performanceSeats.size(), 0);
        assertEquals(performanceSeats.size(), 2);
        assertEquals(performanceSeats.get(0).getStatus(), SeatStatus.PENDING);
    }

    @Test
    void pendingSeats() {
        // given
        Long userId = 100001L;
        Performance performance = em.find(Performance.class, 100001L);
        List<Long> seatIds = List.of(100001L, 100002L);

        // when
        bookingService.pendingSeats(userId, performance, seatIds);

        // then
        List<PerformanceSeat> performanceSeats = performanceSeatRepository.findByPerformanceAndBySeats(performance.getId(), seatIds)
                .stream()
                .filter(ps -> ps.getStatus().equals(SeatStatus.PENDING))
                .collect(Collectors.toList());
        assertEquals(performanceSeats.size(), 2);
    }

    @Test
    void bookingSecondStep() {
        // given
        Long performanceId = 100001L;
        Long userId = 100002L;
        User user = em.find(User.class, userId);
        PaymentByCardDto cardDto = new PaymentByCardDto();
        cardDto.setBank(Bank.KB);
        cardDto.setCardNumber("1234-1234-1234-1234");
        cardDto.setCardExpiration("12/26");
        cardDto.setCardCVV(123);
        DeliveryCreateDto deliveryDto = new DeliveryCreateDto();
        deliveryDto.setReceiverName(user.getName());
        deliveryDto.setPhone("010-1234-1234");
        deliveryDto.setZipCode(12345L);
        deliveryDto.setMainAddress("경기도");
        deliveryDto.setDetailAddress("경기도청");
        deliveryDto.setMessage("문 앞에 놔주세요.");
        BookingSecondDto dto = BookingSecondDto.builder()
                .performanceId(performanceId)
                .userId(userId)
                .paymentMethod(PaymentMethod.CREDIT_CARD)
                .cardDto(cardDto)
                .deliveryDto(deliveryDto)
                .build();

        // when
        bookingService.bookingSecondStep(dto);

        // then
        // payment, paymentItem, ticket, delivery 조회
        List<Ticket> tickets = ticketRepository.findByUser(userId);
        assertNotEquals(tickets.size(), 0);
    }
}