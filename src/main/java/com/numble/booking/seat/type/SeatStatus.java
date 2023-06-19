package com.numble.booking.seat.type;

import lombok.Getter;

/**
 * <pre>
 * Class Name : SeatStatus
 * Description : 공연 좌석 상태
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
public enum SeatStatus {
    AVAILABLE("예약가능한 좌석", 1),
    OCCUPIED("예약된 좌석", 2),
    BLOCKED("막힘", 3),
    UNAVAILABLE("이용불가", 4),
    PENDING("이미 선택된 좌석", 5)
    ;

    private final String name;
    private final Integer order;

    SeatStatus(String name, Integer order) {
        this.name = name;
        this.order = order;
    }
}
