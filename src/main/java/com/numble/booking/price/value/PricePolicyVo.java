package com.numble.booking.price.value;

import com.numble.booking.seat.type.SeatType;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * Class Name : PricePolicyVo
 * Description : 공연 좌석별 가격 VO
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
@NoArgsConstructor
public class PricePolicyVo {

    private SeatType type;

    private Integer price;
}
