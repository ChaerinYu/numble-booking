package com.numble.booking.performance.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.numble.booking.annotation.BookingTest;
import com.numble.booking.performance.domain.PerformanceSeat;
import com.numble.booking.seat.type.SeatStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <pre>
 * Class Name : PerformanceSeatRepositoryTest
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-01-30 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2024-01-30
 */
@BookingTest
class PerformanceSeatRepositoryTest {

    @Autowired
    private PerformanceSeatRepository performanceSeatRepository;

    @Test
    @DisplayName("공연 좌석 in절 찾기")
    void findByPerformanceSeats() {
        // given
        List<Long> ids = List.of(100000L, 100001L, 100002L);
        // when
        List<PerformanceSeat> performanceSeats = performanceSeatRepository.findByPerformanceSeats(ids);
        // then
        assertThat(performanceSeats).isNotEmpty();
        assertThat(performanceSeats.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("공연 id와 사용자 id로 예약 좌석 찾기")
    void findByPerformanceAndUser() {
        // given
        Long performanceId = 1000L;
        Long userId = 1003L;
        // when
        List<PerformanceSeat> performanceSeats = performanceSeatRepository.findByPerformanceAndUser(performanceId, userId);
        // then
        assertThat(performanceSeats).isNotEmpty();
        assertThat(performanceSeats.size()).isEqualTo(2);
        assertThat(performanceSeats.get(0).getStatus()).isEqualTo(SeatStatus.PENDING);
    }
}