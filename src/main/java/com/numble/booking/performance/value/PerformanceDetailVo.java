package com.numble.booking.performance.value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.numble.booking.performance.domain.Performance;
import com.numble.booking.performance.type.Genre;
import com.numble.booking.price.value.PricePolicyVo;
import com.numble.booking.util.MapperUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * <pre>
 * Class Name : PerformanceDetailVo
 * Description : 공연 상세 VO
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
@Getter
@Setter
@NoArgsConstructor
public class PerformanceDetailVo {

    // 공연 ID
    private Long performanceId;

    // 공연장 ID
    private Long venueId;

    // 공연장 이름
    private String venueName;

    // 공연 이름
    private String name;

    // 공연 설명
    private String description;

    // 공연 장르
    private Genre genre;

    // 공연 일자
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    // 공연 시작 시간
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    // 공연 종료 시간
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;

    // 공연 좌석별 금액
    private List<PricePolicyVo> prices;

    public static PerformanceDetailVo of(Performance performance, List<PricePolicyVo> pricePolicies) {
        PerformanceDetailVo vo = MapperUtil.map(performance, PerformanceDetailVo.class);
        vo.setPerformanceId(performance.getId());
        vo.setVenueId(performance.getVenue().getId());
        vo.setVenueName(performance.getVenue().getName());
        vo.setPrices(pricePolicies);
        return vo;
    }
}
