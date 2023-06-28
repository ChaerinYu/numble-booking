package com.numble.booking.book.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.numble.booking.book.value.BookingFirstDto;
import com.numble.booking.performance.domain.PerformanceSeat;
import com.numble.booking.performance.repository.PerformanceSeatRepository;
import com.numble.booking.seat.type.SeatStatus;
import com.numble.booking.seat.value.SeatBookingDto;
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
@SpringBootTest
@Transactional
class BookingServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private PerformanceSeatRepository performanceSeatRepository;

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
        assertEquals(performanceSeats.get(0).getStatus(), SeatStatus.PENDING);
    }

    @Test
    void pendingSeats() {
    }

    @Test
    void bookingSecondStep() {
    }
}