package com.numble.booking.web.security;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numble.booking.user.service.UserLoginService;
import com.numble.booking.user.type.UserStatus;
import com.numble.booking.web.security.value.UserLoginDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

/**
 * <pre>
 * Class Name : CustomAuthenticationFilter
 * Description : 아이디와 비밀번호 기반의 데이터를 Form 데이터로 전송을 받아 '인증'을 담당하는 필터입니다.
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
@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserLoginService userLoginService;
    private final ObjectMapper objectMapper;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, UserLoginService userLoginService, ObjectMapper objectMapper) {
        this.userLoginService = userLoginService;
        this.objectMapper = objectMapper;
        super.setAuthenticationManager(authenticationManager);
    }

    /**
     * 지정된 URL로 form 전송을 하였을 경우 파라미터 정보를 가져온다.
     *
     * @param request  from which to extract parameters and perform the authentication
     * @param response the response, which may be needed if the implementation has to do a
     *                 redirect as part of a multi-stage authentication process (such as OpenID).
     * @return Authentication {}
     * @throws AuthenticationException {}
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authRequest;
        try {
            authRequest = getAuthRequest(request);
            setDetails(request, authRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * Request로 받은 ID와 패스워드 기반으로 토큰을 발급한다.
     */
    private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) {
        try {
            /**
             * POST로 넘어왔는지 체크
             */
            boolean postOnly = true;
            if (postOnly && !request.getMethod().equals("POST")) {
                throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
            }
            String username, password;
            try {
                UserLoginDto dto = objectMapper.readValue(IOUtils.toString(request.getReader()), UserLoginDto.class);
                username = dto.getUsername();
                password = dto.getPassword();
            } catch (IOException e) {
                throw new RuntimeException("Body를 읽어오는 도중 에러가 발생했습니다.");
            }

            request.setAttribute("username", username);
            request.setAttribute("password", password);

            //로그인 실패를 연속으로 5회 이상 한 경우 차단
            int lockedRemainCount = userLoginService.findLoginFailCountByLoginId(username);
            if(lockedRemainCount > 4) {
                throw new LockedException(UserStatus.LOCKED.name());
            }

            Set<GrantedAuthority> authorities = new HashSet<>();

            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
