package com.numble.booking.user.exception;

/**
 * <pre>
 * Class Name : NotFoundUserException
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-18	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-18
 */
public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException() {
        super("존재하지 않는 회원입니다.");
    }
}
