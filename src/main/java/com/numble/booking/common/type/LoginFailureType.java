package com.numble.booking.common.type;

import lombok.Getter;

/**
 * <pre>
 * Class Name : LoginFailureType
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
@Getter
public enum LoginFailureType {
    WRONG_ID_PW("비밀번호가 일치하지 않습니다."),
    INACTIVE_USER("탈퇴한 회원입니다.")
    ;

    private final String message;

    LoginFailureType(String message) {
        this.message = message;
    }
}
