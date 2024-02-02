package com.numble.booking.web.security.domain;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.numble.booking.user.value.UserVo;
import lombok.Getter;

/**
 * <pre>
 * Class Name : CustomUser
 * Description : Security User 커스텀 클래스
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-01-31 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2024-01-31
 */
@Getter
public class CustomUser extends User {
    private UserVo userVo;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, UserVo userVo) {
        super(username, password, authorities);
        this.userVo = userVo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomUser customUser = (CustomUser) o;
        return Objects.equals(getUsername(), customUser.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }
}
