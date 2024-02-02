package com.numble.booking.seat.value;

import com.numble.booking.seat.domain.Seat;
import com.numble.booking.seat.type.SeatType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * Class Name : VenueSeatListVo
 * Description : 공연장 좌석 vo
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
@ApiModel("공연장 좌석 목록")
@Getter
@Setter
@NoArgsConstructor
public class VenueSeatListVo {

    @ApiModelProperty("좌석 번호")
    private String seatNumber;

    @ApiModelProperty(value = "좌석 유형", example = "REGULAR")
    private SeatType seatType;

    public static VenueSeatListVo of(Seat entity) {
        VenueSeatListVo vo = new VenueSeatListVo();
        vo.setSeatNumber(entity.getSeatNumber());
        vo.setSeatType(entity.getSeatType());
        return vo;
    }
}
