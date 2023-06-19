package com.numble.booking.ticket.type;

import lombok.Getter;

/**
 * <pre>
 * Class Name : ReceivingMethod
 * Description : 티켓 수령 방법
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
public enum ReceivingMethod {

    PRINT_AT_HOME("집에서 직접 인쇄", 1),
    MOBILE_TICKET("모바일 티켓", 2),
    WILL_CALL("현장 수령", 3),
    POSTAL_MAIL("배송", 4),
    TICKET_KIOSK("공연장 내 키오스크", 5)
    ;

    private final String name;
    private final Integer order;

    ReceivingMethod(String name, Integer order) {
        this.name = name;
        this.order = order;
    }
}
