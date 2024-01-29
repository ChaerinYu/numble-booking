package com.numble.booking.book.service;

import com.numble.booking.annotation.BookingTest;
import com.numble.booking.book.value.BookingFirstDto;
import com.numble.booking.book.value.BookingSecondDto;
import com.numble.booking.delivery.type.DeliveryStatus;
import com.numble.booking.delivery.value.DeliveryCreateDto;
import com.numble.booking.order.domain.Order;
import com.numble.booking.performance.domain.Performance;
import com.numble.booking.performance.domain.PerformanceSeat;
import com.numble.booking.seat.type.SeatStatus;
import com.numble.booking.seat.value.SeatBookingDto;
import com.numble.booking.ticket.domain.Ticket;
import com.numble.booking.ticket.type.TicketStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <pre>
 * Class Name : BookingServiceTest
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-01-29	    chaerin	New
 * </pre>
 *
 * @author chaerin
 * @since 2024-01-29
 */
@BookingTest
class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("예약 1단계")
    void bookingFirstStep() {
        // given
        Long performanceId = 1001L;
        List<SeatBookingDto> seats = new ArrayList<>();
        seats.add(SeatBookingDto.builder().performanceSeatId(100010L).seatNumber("1").build());
        seats.add(SeatBookingDto.builder().performanceSeatId(100011L).seatNumber("2").build());
        Long userId = 1001L;
        BookingFirstDto dto = new BookingFirstDto();
        dto.setPerformanceId(performanceId);
        dto.setSeats(seats);
        dto.setUserId(userId);

        // when
        Long returnPerformanceId = bookingService.bookingFirstStep(dto);

        // then
        assertThat(returnPerformanceId).isEqualTo(performanceId);

        Performance performance = em.find(Performance.class, performanceId);
        List<PerformanceSeat> results = em.createQuery("SELECT ps FROM PerformanceSeat ps INNER JOIN ps.performance p " +
                        "WHERE p.id = :performanceId", PerformanceSeat.class)
                .setParameter("performanceId", performanceId)
                .getResultList();

        assertThat(results.size()).isEqualTo(performance.getCapacity().intValue()); // 해당 공연은 공연장 capacity 그대로 사용함
        List<PerformanceSeat> pendingSeats = results.stream().filter(r -> r.getStatus().equals(SeatStatus.PENDING)).collect(Collectors.toList());
        assertThat(pendingSeats.size()).isEqualTo(2);
        assertThat(pendingSeats.get(0).getUser().getId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("좌석 PENDING 처리")
    void pendingSeats() {
        // given
        List<Long> performanceSeatIds = List.of(110000L, 110001L);
        Long userId = 1003L;

        // when
        bookingService.pendingSeats(userId, performanceSeatIds);

        // then
        PerformanceSeat performanceSeat1 = em.find(PerformanceSeat.class, 110000L);
        PerformanceSeat performanceSeat2 = em.find(PerformanceSeat.class, 110001L);
        assertThat(performanceSeat1.getStatus()).isEqualTo(SeatStatus.PENDING);
        assertThat(performanceSeat2.getStatus()).isEqualTo(SeatStatus.PENDING);
    }

    @Test
    @DisplayName("예약 2단계: 배송지/결제 저장하고 OrderItem, Ticket 생성하여 예약 확정")
    void bookingSecondStep() {
        // given
        BookingSecondDto dto = new BookingSecondDto();
        Long performanceId = 1000L;
        Long userId = 1003L;
        DeliveryCreateDto deliveryCreateDto = new DeliveryCreateDto();
        deliveryCreateDto.setReceiverName("유채린");
        deliveryCreateDto.setPhone("010-1234-1234");
        deliveryCreateDto.setZipCode(12345L);
        deliveryCreateDto.setMainAddress("서울시");
        deliveryCreateDto.setDetailAddress("마포구");
        deliveryCreateDto.setMessage("문 앞에 놔주세요.");
        dto.setPerformanceId(performanceId);
        dto.setDeliveryDto(deliveryCreateDto);
        dto.setUserId(userId);

        // when
        bookingService.bookingSecondStep(dto);

        // then
        List<Order> orders = em.createQuery("SELECT o FROM Order o INNER JOIN o.user u WHERE u.id = :userId", Order.class)
                .setParameter("userId", userId)
                .getResultList();

        assertThat(orders).isNotEmpty();
        assertThat(orders.size()).isEqualTo(1);

        Order order = orders.get(0);
        assertThat(order.getDelivery().getStatus()).isEqualTo(DeliveryStatus.PURCHASE);
        assertThat(order.getDelivery().getPhone()).isEqualTo("010-1234-1234");

        List<PerformanceSeat> performanceSeats = em.createQuery("SELECT ps FROM PerformanceSeat ps INNER JOIN ps.performance p INNER JOIN ps.user u " + "" +
                        "WHERE p.id = :performanceId AND u.id = :userId", PerformanceSeat.class)
                .setParameter("performanceId", performanceId)
                .setParameter("userId", userId)
                .getResultList();
        assertThat(performanceSeats).isNotEmpty();
        PerformanceSeat performanceSeat = performanceSeats.get(0);
        assertThat(performanceSeat.getStatus()).isEqualTo(SeatStatus.OCCUPIED);

        List<Ticket> tickets = em.createQuery("SELECT t FROM Ticket t INNER JOIN t.performanceSeat ps WHERE ps.id = :performanceSeatId", Ticket.class)
                .setParameter("performanceSeatId", performanceSeat.getId())
                .getResultList();
        assertThat(tickets).isNotEmpty();
        Ticket ticket = tickets.get(0);
        assertThat(ticket.getStatus()).isEqualTo(TicketStatus.CONFIRMED);
    }
}