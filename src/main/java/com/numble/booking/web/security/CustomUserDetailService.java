package com.numble.booking.web.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.numble.booking.user.domian.User;
import com.numble.booking.user.exception.NotFoundUserException;
import com.numble.booking.user.repository.UserRepository;
import com.numble.booking.user.service.UserLoginService;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * Class Name : CustomUserDetailService
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-07-06 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-07-06
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserLoginService userLoginService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByLoginId(username)
                .orElseThrow(NotFoundUserException::new);
        return userLoginService.getCustomUser(user);
    }
}
