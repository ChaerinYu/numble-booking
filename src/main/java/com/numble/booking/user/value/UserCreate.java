package com.numble.booking.user.value;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <pre>
 * Class Name : UserCreate
 * Description : convertToUserCreateDto를 통해 UserCreateDto로 변환한 후 등록 진행한다.
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
public interface UserCreate {
    UserCreateDto convertToUserCreateDto(PasswordEncoder encoder);
}
