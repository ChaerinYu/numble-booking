package com.numble.booking.user.service;

import java.util.HashSet;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.numble.booking.user.domian.User;
import com.numble.booking.user.exception.NotFoundUserException;
import com.numble.booking.user.repository.UserRepository;
import com.numble.booking.user.type.UserStatus;
import com.numble.booking.user.value.UserVo;
import com.numble.booking.web.security.domain.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * Class Name : UserLoginService
 * Description :
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
@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final UserRepository userRepository;

    /**
     * 유효한 사용자의 CustomUser를 가져온다.
     * 유효하지 않은 사용자의 경우 Exception이 발생한다.
     */
    public UserDetails findCustomUser(String username) {
        User user = userRepository.findByLoginId(username)
                .orElseThrow(NotFoundUserException::new);

        // 탈퇴 회원
        if (UserStatus.CLOSED.equals(user.getStatus())) {
            throw new DisabledException(UserStatus.CLOSED.name());
        }

        // 비밀번호 변경
        if (UserStatus.LOCKED.equals(user.getStatus())) {
            throw new DisabledException(UserStatus.LOCKED.name());
        }

        UserVo userVo = new UserVo(user);
        return new CustomUser(user.getLoginId(), user.getPassword(), new HashSet<>(), userVo);
    }

    @Transactional
    public void handleLoginSuccessUser(String loginId) {
        User user = userRepository.findByLoginIdIgnoreCase(loginId).orElseThrow(NotFoundUserException::new);
        user.updateLastLoginDate();
    }

    @Transactional(readOnly = true)
    public UserVo findByLoginId(String loginId) {
        User user = userRepository.findByLoginId(loginId).orElseThrow(NotFoundUserException::new);
        return new UserVo(user);
    }
}
