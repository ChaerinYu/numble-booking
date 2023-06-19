package com.numble.booking.payment.type;

import lombok.Getter;

/**
 * <pre>
 * Class Name : PaymentMethod
 * Description : 결제 방식
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
public enum PaymentMethod {
    CREDIT_CARD("신용카드", 1),
    DEBIT_CARD("직불카드", 2),
    E_WALLET("전자지갑", 3)
//    BANK_TRANSFER("무통장", 4)
    ;

    private final String name;
    private final Integer order;

    PaymentMethod(String name, Integer order) {
        this.name = name;
        this.order = order;
    }
}
