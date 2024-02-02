package com.numble.booking.web.api;

import com.numble.booking.common.base.MessageVo;
import com.numble.booking.performance.service.PerformanceSeatService;
import com.numble.booking.performance.service.PerformanceService;
import com.numble.booking.performance.value.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

    @ApiOperation("공연 목록 조회")
    @GetMapping
    public Page<PerformanceListVo> findAll(Pageable pageable, PerformanceFindDto dto) {
        return performanceService.findAll(pageable, dto);
    }

    @ApiOperation("공연 상세 조회")
    @GetMapping("/{performanceId}")
    public PerformanceDetailVo find(@PathVariable Long performanceId) {
        return performanceService.find(performanceId);
    }
    
    @ApiOperation("공연 잔여 좌석 조회")
    @GetMapping("/{performanceId}/seats")
    public PerformanceRemainingSeatsVo findRemainingSeats(@PathVariable Long performanceId) {
        return performanceSeatService.findRemainingSeats(performanceId);
    }

    @ApiOperation("공연 등록")
    @PostMapping
    public MessageVo create(@Valid @RequestBody PerformanceCreateDto dto) {
        Long performanceId = performanceService.create(dto);
        return new MessageVo(HttpStatus.OK, performanceId, "등록되었습니다.");
    }
}
