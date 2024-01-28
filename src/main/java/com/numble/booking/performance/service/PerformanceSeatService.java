package com.numble.booking.performance.service;

import com.numble.booking.performance.repository.PerformanceSeatQuerydslRepository;
import com.numble.booking.performance.value.PerformanceRemainingSeatsVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * Class Name : PerformanceSeatService
 * Description : 공연 좌석 service
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
public class PerformanceSeatService {

    private final PerformanceSeatQuerydslRepository performanceSeatQuerydslRepository;

    @Transactional(readOnly = true)
    public PerformanceRemainingSeatsVo findRemainingSeats(Long performanceId) {
        PerformanceRemainingSeatsVo vo = new PerformanceRemainingSeatsVo();
        vo.setPerformanceId(performanceId);
        vo.setSeats(performanceSeatQuerydslRepository.findAvailableSeats(performanceId));
        return vo;
    }
}
