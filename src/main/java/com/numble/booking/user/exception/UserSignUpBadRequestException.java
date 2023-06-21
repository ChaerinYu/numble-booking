package com.numble.booking.user.exception;

/**
 * <pre>
 * Class Name : UserSignUpBadRequestException
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-21 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-06-21
 */
public class UserSignUpBadRequestException extends RuntimeException {
    public UserSignUpBadRequestException(String message) {
        super(message);
    }
}
