package com.numble.booking.venue.value;

import com.numble.booking.performance.value.PerformanceListVo;
import com.numble.booking.seat.value.VenueSeatListVo;
import com.numble.booking.venue.type.VenuesType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Class Name : VenueDetailVo
 * Description : 공연장 상세 vo
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
@ApiModel("공연장 상세")
@Getter
@Setter
@NoArgsConstructor
public class VenueDetailVo {

    @ApiModelProperty("공연장 ID")
    private Long venueId;

    @ApiModelProperty("공연장 이름")
    private String name;

    @ApiModelProperty("공연장 수용 인원")
    private Long capacity;

    @ApiModelProperty("사용 가능 시간")
    private String possibleTimes;

    @ApiModelProperty("공연장 유형")
    private VenuesType type;

    @ApiModelProperty("공연장 좌석")
    private List<VenueSeatListVo> seats = new ArrayList<>();

    @ApiModelProperty("해당 공연장에서 진행되는 공연 목록")
    private List<PerformanceListVo> performances = new ArrayList<>();
}
