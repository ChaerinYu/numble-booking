package com.numble.booking.payment.type;

import lombok.Getter;

/**
 * <pre>
 * Class Name : PaymentStatus
 * Description : 결제 상태
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
public enum PaymentStatus {
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
    private final Integer order;

    PaymentStatus(String name, Integer order) {
        this.name = name;
        this.order = order;
    }
}
