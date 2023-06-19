package com.numble.booking.seat.value;

import com.numble.booking.seat.type.SeatStatus;
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
@Getter
@NoArgsConstructor
public class SeatListVo {

    private Long seatId;

    private String seatNumber;

    private SeatStatus status;
}
