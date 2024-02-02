package com.numble.booking.web.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numble.booking.user.domian.User;
import com.numble.booking.web.security.domain.CustomUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.numble.booking.web.security.JwtTokenProvider;
import com.numble.booking.web.security.dto.UserLoginDto;

/**
 * <pre>
 * Class Name : JwtAuthenticationFilter
 * Description : spring security JWT Token filter
 * 로그인 실행 후 토큰을 response에 담아준다.
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
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                                   ObjectMapper objectMapper) {
        super.setAuthenticationManager(authenticationManager);
        this.jwtTokenProvider = jwtTokenProvider;
        this.objectMapper = objectMapper;
    }

    /**
     * 요청에서 사용자 정보 가져와서 인증
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // 로그인은 일반적으로 POST 요청이기 때문에 HTTP 요청 메서드가 POST가 아닌 경우 예외를 던짐
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        UserLoginDto user;
        try {
            user = objectMapper.readValue(request.getInputStream(), UserLoginDto.class);
        } catch (IOException e) {
            throw new RuntimeException("로그인하는 도중 오류가 발생했습니다.");
        }
        // HTTP 요청에서 사용자 이름과 비밀번호를 추출
        String username = user.getUsername();
        String password = user.getPassword();

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        // 사용자 이름과 비밀번호를 이용하여 token 생성
        UsernamePasswordAuthenticationToken authRequest = user.createAuthRequest();
        // 부가적인 "details" 속성 설정을 허용
        setDetails(request, authRequest);

        // AuthenticationManager를 통해 실제 인증 시도
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 토큰 생성
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        // 로그인이 성공 했음으로, Cookie 와 Header 에 Token 정보를 전송 한다.
        Object principal = authResult.getPrincipal();
        if (principal instanceof UserDetails) {
            CustomUser cu = (CustomUser) principal;
            try {
                String token = jwtTokenProvider.createToken(cu);
                request.setAttribute("token", token);
            } catch (Exception e) {
                throw new IllegalArgumentException("인증 Token 생성 실패. 관리자에게 문의 하시기 바랍니다.");
            }
        }
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
