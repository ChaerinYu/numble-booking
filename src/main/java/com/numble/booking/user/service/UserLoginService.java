package com.numble.booking.user.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.numble.booking.user.domian.User;
import com.numble.booking.user.type.UserStatus;
import com.numble.booking.user.value.UserVo;
import com.numble.booking.web.security.CustomUser;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * Class Name : UserLoginService
 * Description :
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
@Service
@RequiredArgsConstructor
public class UserLoginService {

    /**
     * 유효하지 않은 사용자의 경우 Exception 발생
     */
    public CustomUser getCustomUser(User user) {

        // 탈퇴 회원
        if (UserStatus.CLOSED.equals(user.getStatus())) {
            throw new DisabledException(UserStatus.CLOSED.name());
        }

        UserVo userVo = UserVo.of(user);
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new CustomUser(user.getLoginId(), user.getPassword(), authorities, userVo);
    }

    /**
     * TODO
     * 로그인ID에 해당하는 유저의 로그인 실패 횟수를 조회한다.
     */
    public int findLoginFailCountByLoginId(String loginId) {
        return 0;
    }
}
