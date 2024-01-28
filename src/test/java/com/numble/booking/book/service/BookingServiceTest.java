package com.numble.booking.book.service;

import com.numble.booking.annotation.BookingTest;
import com.numble.booking.book.value.BookingFirstDto;
import com.numble.booking.performance.domain.Performance;
import com.numble.booking.performance.domain.PerformanceSeat;
import com.numble.booking.seat.type.SeatStatus;
import com.numble.booking.seat.value.SeatBookingDto;
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
    }

    @Test
    @DisplayName("예약 2단계: 배송지/결제 저장하고 OrderItem, Ticket 생성하여 예약 확정")
    void bookingSecondStep() {
    }
}