package com.numble.booking.web.security;

import com.numble.booking.common.type.LoginFailureType;
import com.numble.booking.user.service.UserLoginService;
import com.numble.booking.user.type.UserStatus;
import com.numble.booking.user.value.UserVo;
import com.numble.booking.web.security.exception.CustomAuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <pre>
 * Class Name : CustomDaoAuthenticationProvider
 * Description : AuthenticationProvider 커스텀
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-02-02	    user	New
 * </pre>
 *
 * @author user
 * @since 2024-02-02
 */
@RequiredArgsConstructor
public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {
    private final UserLoginService userLoginService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        super.additionalAuthenticationChecks(userDetails, authentication);
        String loginId = authentication.getName();
        UserVo userVo = userLoginService.findByLoginId(loginId);

        // 비밀번호
        if (authentication.getCredentials() == null) {
            throw new CustomAuthenticationException(LoginFailureType.WRONG_ID_PW);
        }

        // 탈퇴 회원
        if (UserStatus.CLOSED.equals(userVo.getStatus())) {
            throw new CustomAuthenticationException(LoginFailureType.INACTIVE_USER);
        }
    }
}
