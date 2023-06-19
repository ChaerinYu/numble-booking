package com.numble.booking.performance.type;

import lombok.Getter;

/**
 * <pre>
 * Class Name : Genre
 * Description : 공연 장르
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
public enum Genre {
    THEATER("영화", 1),
    CONCERT("콘서트", 2),
    OPERA("오페라", 3),
    MUSICAL("뮤지컬", 4)
    ;

    private final String name;
    private final Integer order;

    Genre(String name, Integer order) {
        this.name = name;
        this.order = order;
    }
}
