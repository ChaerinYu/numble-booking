package com.numble.booking.user.type;

import lombok.Getter;

/**
 * <pre>
 * Class Name : BusinessType
 * Description :
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
public enum BusinessType {
    VENUE_MANAGER("공연장 관리자", 1),
    PERFORMANCE_MANAGER("공연 관리자", 2),
    ;

    private final String name;
    private final Integer order;

    BusinessType(String name, Integer order) {
        this.name = name;
        this.order = order;
    }
}
