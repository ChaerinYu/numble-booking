package com.numble.booking.performance.exception;

/**
 * <pre>
 * Class Name : NotFoundPerformanceException
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
public class NotFoundPerformanceException extends RuntimeException {
    public NotFoundPerformanceException() {
        super("존재하지 않는 공연입니다.");
    }
}
