package com.numble.booking.order.exception;

/**
 * <pre>
 * Class Name : BadRequestOrderException
 * Description : 결제 오류
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-19	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-06-19
 */
public class BadRequestOrderException extends RuntimeException {
    public BadRequestOrderException() {
        super("잘못된 요청입니다.");
    }

    public BadRequestOrderException(String message) {
        super(message);
    }
}
