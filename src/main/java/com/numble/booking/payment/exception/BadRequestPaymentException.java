package com.numble.booking.payment.exception;

/**
 * <pre>
 * Class Name : BadRequestPaymentException
 * Description : 결제 오류
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-19	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-19
 */
public class BadRequestPaymentException extends RuntimeException {
    public BadRequestPaymentException() {
        super("[결제 오류] 잘못된 요청입니다.");
    }

    public BadRequestPaymentException(String message) {
        super(message);
    }
}
