package com.numble.booking.price.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.numble.booking.annotation.BookingTest;
import com.numble.booking.price.domain.PricePolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <pre>
 * Class Name : PricePolicyRepositoryTest
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
class PricePolicyRepositoryTest {

    @Autowired
    private PricePolicyRepository pricePolicyRepository;

    @Test
    void findByPerformance() {
        // given
        Long performanceId = 1200L;
        // when
        List<PricePolicy> policies = pricePolicyRepository.findByPerformance(performanceId);
        // then
        assertThat(policies).isNotEmpty();
        assertThat(policies.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("가격 정책 공연id in절 검색")
    void findByPerformanceIn() {
        // given
        List<Long> ids = List.of(1100L, 1200L);
        // when
        List<PricePolicy> policies = pricePolicyRepository.findByPerformanceIn(ids);
        // then
        assertThat(policies).isNotEmpty();
        assertThat(policies.size()).isEqualTo(4);
    }
}