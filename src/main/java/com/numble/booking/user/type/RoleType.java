package com.numble.booking.user.type;

import lombok.Getter;

/**
 * <pre>
 * Class Name : RoleType
 * Description : 권한 유형
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
@Getter
public enum RoleType {
    ADMIN("관리자"),
    BUSINESS("사업계정"),
    MEMBER("일반유저")
    ;

    private final String name;

    RoleType(String name) {
        this.name = name;
    }
}
