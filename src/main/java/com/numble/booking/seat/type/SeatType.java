package com.numble.booking.seat.type;

import lombok.Getter;

/**
 * <pre>
 * Class Name : SeatType
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-14	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-14
 */
@Getter
public enum SeatType {
    PREMIUM("프리미엄", 1),
    VIP("VIP", 2),
    REGULAR("일반", 3),
    ACCESSIBLE("휠체어", 4)
    ;

    private final String name;
    private final Integer order;

    SeatType(String name, Integer order) {
        this.name = name;
        this.order = order;
    }
}
