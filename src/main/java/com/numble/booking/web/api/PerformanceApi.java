package com.numble.booking.web.api;

import com.numble.booking.performance.service.PerformanceSeatService;
import com.numble.booking.performance.service.PerformanceService;
import com.numble.booking.performance.value.*;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <pre>
 * Class Name : PerformanceApi
 * Description : 공연 API
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-15	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-15
 */
@Api(value = "Performance APIs")
@RestController
@RequiredArgsConstructor
@RequestMapping("/performances")
public class PerformanceApi {
    private final PerformanceService performanceService;
    private final PerformanceSeatService performanceSeatService;

    /**
     * 공연 목록 조회
     */
    @GetMapping
    public Page<PerformanceListVo> findAll(Pageable pageable, PerformanceFindDto dto) {
        return performanceService.findAll(pageable, dto);
    }

    /**
     * 공연 정보 조회
     */
    @GetMapping("/{performanceId}")
    public PerformanceDetailVo find(@PathVariable Long performanceId) {
        return performanceService.find(performanceId);
    }

    /**
     * 공연 잔여 좌석 조회
     */
    @GetMapping("/{performanceId}/seats")
    public PerformanceRemainingSeatsVo findRemainingSeats(@PathVariable Long performanceId) {
        return performanceSeatService.findRemainingSeats(performanceId);
    }

    /**
     * 공연 등록
     */
    @PostMapping
    public Long create(@Valid @RequestBody PerformanceCreateDto dto) {
        return performanceService.create(dto);
    }
}
