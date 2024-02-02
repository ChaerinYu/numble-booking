package com.numble.booking.performance.value;

import com.numble.booking.seat.value.SeatListVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * <pre>
 * Class Name : PerformanceRemainingSeatsVo
 * Description : 남은 좌석 vo
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-17	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-17
 */
@ApiModel("공연 남은 좌석 목록")
@Getter
@Setter
@NoArgsConstructor
public class PerformanceRemainingSeatsVo {

    @ApiModelProperty("공연 ID")
    private Long performanceId;

    @ApiModelProperty("남은 좌석")
    private List<SeatListVo> seats;
}
