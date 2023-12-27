package com.numble.booking.delivery.type;

import lombok.Getter;

/**
 * <pre>
 * Class Name : DeliveryStatus
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
public enum DeliveryStatus {
    PURCHASE("구매완료", 1),
    PREPARE("상품준비", 2),
    DELIVERY("배송중", 3),
    DONE("배송완료", 4)
    ;

    private final String name;
    private final int order;

    DeliveryStatus(String name, int order) {
        this.name = name;
        this.order = order;
    }
}
