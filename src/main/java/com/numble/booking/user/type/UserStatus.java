package com.numble.booking.user.type;

import lombok.Getter;

/**
 * <pre>
 * Class Name : UserStatus
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-15	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-15
 */
@Getter
public enum UserStatus {
    ACTIVE("사용", 1),
    INACTIVE("휴면", 2),
    CHANGE_PASSWORD_THREE_MONTH("3개월 비밀 번호 미변경", 3),
    CLOSED("탈퇴", 4),
    LOCKED("비밀 번호 잠김", 5)
    ;

    private final String name;
    private final Integer order;

    UserStatus(String name, Integer order) {
        this.name = name;
        this.order = order;
    }
}
