package com.numble.booking.ticket.exception;

/**
 * <pre>
 * Class Name : NotFoundTicketKeyException
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-20	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-20
 */
public class NotFoundTicketKeyException extends RuntimeException {
    public NotFoundTicketKeyException() {
        super("유효하지 않은 티켓 키 값입니다.");
    }
}
