package com.numble.booking.web.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.numble.booking.user.service.UserLoginService;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * Class Name : CustomUserDetailsService
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
public class CustomUserDetailsService implements UserDetailsService {

    private final UserLoginService userLoginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userLoginService.findCustomUser(username);
    }
}
