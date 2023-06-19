package com.numble.booking.performance.exception;

/**
 * <pre>
 * Class Name : NotFoundPerformanceSeatException
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
public class NotFoundPerformanceSeatException extends RuntimeException {
    public NotFoundPerformanceSeatException(String message) {
        super(message);
    }
}
