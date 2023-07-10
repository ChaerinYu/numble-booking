package com.numble.booking.web.security.value;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 * Class Name : UserLoginDto
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-07-10 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-07-10
 */
@Getter
@Setter
public class UserLoginDto {
    private String username;
    private String password;

    public String getUsername() {
        return StringUtils.isEmpty(username) ? "" : username.toUpperCase();
    }
}
