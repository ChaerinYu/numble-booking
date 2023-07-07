package com.numble.booking.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 * Class Name : JwtAuthenticationFilter
 * Description : login 시 걸리는 filter
 * 사용자가 입력한 로그인 정보를 인터셉트해서 AuthenticationManager에게 Authentication 객체를 넘겨 준다.
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
@Slf4j
@NoArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private boolean postOnly = true;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    /**
     * 해당 필터를 인증 프로세스 이전에 요청에서 사용자 정보를 가져 와서
     * Authentication 객체를 인증 프로세스 객체에게 전달하는 역할
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("JwtAuthenticationFilter.attemptAuthentication");

        /**
         * POST로 넘어왔는지 체크
         */
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        if (StringUtils.isEmpty(username)) {
            username = "";
        }
        if (StringUtils.isEmpty(password)) {
            password = "";
        }

        username = username.trim();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
