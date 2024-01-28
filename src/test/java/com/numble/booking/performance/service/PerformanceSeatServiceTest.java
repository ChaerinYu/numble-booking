package com.numble.booking.performance.service;

import com.numble.booking.annotation.BookingTest;
import com.numble.booking.performance.value.PerformanceRemainingSeatsVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <pre>
 * Class Name : PerformanceSeatServiceTest
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
class PerformanceSeatServiceTest {

    @Autowired
    private PerformanceSeatService performanceSeatService;

    @Test
    @DisplayName("남아있는 좌석 조회")
    void findRemainingSeats() {
        // given
        Long performanceId = 1000L;
        // when
        PerformanceRemainingSeatsVo remainingSeats = performanceSeatService.findRemainingSeats(performanceId);
        // then
        assertThat(remainingSeats.getPerformanceId()).isEqualTo(performanceId);
        assertThat(remainingSeats.getSeats().size()).isEqualTo(2);
    }
}