package com.numble.booking.user.value;

import com.numble.booking.user.type.BusinessType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * Class Name : UserCreateDto
 * Description : 사용자 생성 DTO
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
@Getter
@Setter
@NoArgsConstructor
public class UserCreateDto {

    // 비밀번호
    private String password;

    // 로그인 ID(사번)
    private String loginId;

    // 이름
    private String name;

    // 이메일
    private String email;

    // 사업자용
    // 사업자 번호
    private String businessLicense;

    // 사업자 유형
    private BusinessType type;

}
