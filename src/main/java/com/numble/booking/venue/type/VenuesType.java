package com.numble.booking.venue.type;

import lombok.Getter;

/**
 * <pre>
 * Class Name : VenuesType
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
public enum VenuesType {
    FIXED_SEAT("고정좌석", 1),
    GENERAL_ADMISSION("자유좌석", 2),
    STANDING("스탠딩", 3),
    AMPHITHEATER("야외공연", 4),
    LOUNGE("라운지", 5)
    ;

    private String name;
    private Integer order;

    VenuesType(String name, Integer order) {
        this.name = name;
        this.order = order;
    }
}
