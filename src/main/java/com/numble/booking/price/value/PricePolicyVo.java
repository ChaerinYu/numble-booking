package com.numble.booking.price.value;

import com.numble.booking.price.domain.PricePolicy;
import com.numble.booking.seat.type.SeatType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Setter
@NoArgsConstructor
public class PricePolicyVo {

    private SeatType seatType;

    private Integer price;

    public static PricePolicyVo of(PricePolicy entity) {
        PricePolicyVo vo = new PricePolicyVo();
        vo.setSeatType(entity.getSeatType());
        vo.setPrice(entity.getPrice());
        return vo;
    }
}
