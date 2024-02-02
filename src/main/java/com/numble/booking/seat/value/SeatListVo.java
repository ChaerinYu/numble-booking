package com.numble.booking.seat.value;

import com.numble.booking.seat.type.SeatStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * Class Name : SeatListVo
 * Description : 공연 좌석 vo
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
@ApiModel("공연 좌석 목록")
@Getter
@NoArgsConstructor
public class SeatListVo {
    
    @ApiModelProperty("좌석 ID")
    private Long seatId;

    @ApiModelProperty("좌석 번호")
    private String seatNumber;

    @ApiModelProperty(value = "좌석 상태", example = "AVAILABLE")
    private SeatStatus status;
}
