package com.numble.booking.performance.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.numble.booking.annotation.BookingTest;
import com.numble.booking.performance.domain.Performance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <pre>
 * Class Name : PerformanceRepositoryTest
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
class PerformanceRepositoryTest {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Test
    @DisplayName("공연장 id로 공연 목록 찾기")
    void findByVenue() {
        // given
        Long venueId = 1000L;
        // when
        List<Performance> performances = performanceRepository.findByVenue(venueId);
        // then
        assertThat(performances).isNotEmpty();
        assertThat(performances.get(0).getId()).isEqualTo(1000L);
    }
}