package com.numble.booking.order.exception;

/**
 * <pre>
 * Class Name : NotFoundOrderException
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-01-29 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2024-01-29
 */
public class NotFoundOrderException extends RuntimeException {
    public NotFoundOrderException(Long orderId) {
        super("존재하지 않는 주문입니다. :" + orderId);
    }
}
