package com.numble.booking.order.type;

import lombok.Getter;

/**
 * <pre>
 * Class Name : OrderStatus
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-12-27	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-12-27
 */
@Getter
public enum OrderStatus {
    APPROVED("승인", 1),
    FAILED("실패", 2),
    BLOCKED("막힘", 3),
    PENDING("대기", 4),
    CANCELED("취소", 5),
    REFUNDED("환불", 6),
    PARTIALLY_APPROVED("일부 승인", 7),
    EXPIRED("만료", 8)
    ;

    private final String name;
    private final int order;

    OrderStatus(String name, int order) {
        this.name = name;
        this.order = order;
    }
}
