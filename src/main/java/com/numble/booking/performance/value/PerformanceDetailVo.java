package com.numble.booking.performance.value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.numble.booking.performance.type.Genre;
import com.numble.booking.price.value.PricePolicyVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("공연 상세")
@Getter
@Setter
@NoArgsConstructor
public class PerformanceDetailVo {

    @ApiModelProperty("공연 ID")
    private Long performanceId;

    @ApiModelProperty("공연장 ID")
    private Long venueId;

    @ApiModelProperty("공연장 이름")
    private String venueName;

    @ApiModelProperty("공연 이름")
    private String name;

    @ApiModelProperty("공연 설명")
    private String description;

    @ApiModelProperty(value = "공연 장르", example = "MUSICAL")
    private Genre genre;

    @ApiModelProperty(value = "공연 일자", example = "2024-01-01")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate performanceDate;

    @ApiModelProperty(value = "공연 시작 시간", example = "13:00")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @ApiModelProperty(value = "공연 종료 시간", example = "15:00")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;

    @ApiModelProperty("공연 좌석별 금액")
    private List<PricePolicyVo> prices;
}
