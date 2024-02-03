package com.numble.booking.web.exception;

/**
 * <pre>
 * Class Name : RoleException
 * Description : 권한 문제로 접근 거절 시 발생하는 Exception
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-02-04	    chaerin	New
 * </pre>
 *
 * @author chaerin
 * @since 2024-02-04
 */
public class RoleException extends RuntimeException {
    public RoleException(String message) {
        super(message);
    }

    public static RoleException isNonMember() {
        return new RoleException("로그인이 필요한 서비스입니다.");
    }

    public static RoleException hasNoRole() {
        return new RoleException("권한이 없습니다.");
    }
}
