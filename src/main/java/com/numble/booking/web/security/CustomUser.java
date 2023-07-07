package com.numble.booking.web.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.numble.booking.user.value.UserVo;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * Class Name : CustomUser
 * Description : Security User 커스텀 클래스
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-07-07 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-07-07
 */
@Getter
@Setter
public class CustomUser extends User {
    private UserVo userVo;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, UserVo userVo) {
        super(username, password, authorities);
        this.userVo = userVo;
    }
}
