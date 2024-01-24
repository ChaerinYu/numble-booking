package com.numble.booking.seat.value;

import com.numble.booking.seat.domain.Seat;
import com.numble.booking.seat.type.SeatType;
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
@Getter
@Setter
@NoArgsConstructor
public class VenueSeatListVo {

    private String seatNumber;

    private SeatType seatType;

    public static VenueSeatListVo of(Seat entity) {
        VenueSeatListVo vo = new VenueSeatListVo();
        vo.setSeatNumber(entity.getSeatNumber());
        vo.setSeatType(entity.getSeatType());
        return vo;
    }
}
