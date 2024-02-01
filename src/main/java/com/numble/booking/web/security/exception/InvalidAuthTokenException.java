package com.numble.booking.web.security.exception;

/**
 * <pre>
 * Class Name : InvalidAuthTokenException
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-02-01	    user	New
 * </pre>
 *
 * @author user
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
