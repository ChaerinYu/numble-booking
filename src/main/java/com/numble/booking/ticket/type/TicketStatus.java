package com.numble.booking.ticket.type;

import lombok.Getter;

/**
 * <pre>
 * Class Name : TicketStatus
 * Description : 티켓 상태
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
public enum TicketStatus {
    
    PENDING("대기", 1, "결제 완료 전 상태"),
    CONFIRMED("예매 확정", 2, "결제 확인 완료, 공연 전 상태"),
    CANCELED("취소", 3, "티켓 구매 취소"),
    EXPIRED("만료", 4, "결제되지 않아 만료된 상태"),
    COMPLETED("예매 완료", 5, "예매 확정된 티켓 중 공연 끝난 경우"),
    REFUNDED("환불", 6, "티켓 구매 후 환불 받은 상태"),
    REFUND_REQUEST("환불 요청", 7, "환불 요청한 경우")
    ;

    private final String name;
    private final Integer order;
    private final String description;

    TicketStatus(String name, Integer order, String description) {
        this.name = name;
        this.order = order;
        this.description = description;
    }
}
