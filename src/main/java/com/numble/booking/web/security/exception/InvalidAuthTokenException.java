package com.numble.booking.web.security.exception;

/**
 * <pre>
 * Class Name : InvalidAuthTokenException
 * Description : 토큰이 만료되거나 잘못된 형식일 때 발생
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-02-01	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2024-02-01
 */
public class InvalidAuthTokenException extends RuntimeException {
    public InvalidAuthTokenException() {
        super("토큰이 만료되었습니다.");
    }

    public InvalidAuthTokenException(String message) {
        super(message);
    }
}
