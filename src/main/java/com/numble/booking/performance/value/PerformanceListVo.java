package com.numble.booking.performance.value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.numble.booking.performance.type.Genre;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <pre>
 * Class Name : PerformanceListVo
 * Description : 공연 목록 VO
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
@ApiModel("공연 목록")
@Getter
@NoArgsConstructor
public class PerformanceListVo {

    @ApiModelProperty("공연 ID")
    private Long performanceId;

    @ApiModelProperty("공연장 ID")
    private Long venueId;

    @ApiModelProperty("공연장 이름")
    private String venueName;

    @ApiModelProperty("공연 이름")
    private String name;

    @ApiModelProperty(value = "공연 장르", example = "MUSICAL")
    private Genre genre;

    @ApiModelProperty(value = "공연 일자", example = "2024-01-01")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate performanceDate;

    @ApiModelProperty(value = "공연 시작 시간", example = "13:00")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @ApiModelProperty(value = "공연 종료 시간", example = "15:00")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;
}
