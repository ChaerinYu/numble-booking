package com.numble.booking.web.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numble.booking.common.base.MessageVo;
import com.numble.booking.web.security.JwtTokenProvider;
import com.numble.booking.web.security.domain.CustomUser;
import com.numble.booking.web.security.exception.InvalidAuthTokenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 * Class Name : JwtAuthorizationFilter
 * Description : Token 정보를 읽어 인증을 진행하고, 인증 정보를 Spring Security Context Holder 에 저장한다.
 *               인증 성공 후에는 Token 을 refresh 하여 인증을 연장한다.
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-02-01	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2024-02-01
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 헤더에서 JWT 를 받아옵니다.
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        // 잘못된 토큰
        try {
            if( token != null && !jwtTokenProvider.validateToken(token) ) {
                throw new InvalidAuthTokenException();
            }

            if (token != null) {
                // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
                Authentication authentication;
                try {
                    // 인증 정보 생성
                    authentication = jwtTokenProvider.getAuthentication(token);
                    // 인증 정보 저장
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    // token refresh (임시 처리 - refresh token 개발 전까지는 인증되면 재생성 한다.)
                    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    if (principal instanceof UserDetails) {
                        createToken((HttpServletRequest) request, (HttpServletResponse) response, (CustomUser) principal);
                    }
                } catch (Exception e) {
                    log.error("Token 인증 실패. {}",e.getMessage());
                    throw new InvalidAuthTokenException("token 인증 실패. 관리자에게 문의 하시기 바랍니다.");
                }
            }
            chain.doFilter(request, response);
        } catch (InvalidAuthTokenException e) {
            handleInvalidAuthTokenException((HttpServletResponse) response, e);
        }
    }

    private void createToken(HttpServletRequest request, HttpServletResponse response, CustomUser principal) {
        try {
            String token = jwtTokenProvider.createToken(principal, request);
            // header, cookie add
            response.setHeader(jwtTokenProvider.getTokenName(), token);

        } catch (Exception e) {
            log.error("Token 생성 실패. {}",e.getMessage());
            throw new IllegalArgumentException("인증 Token 생성 실패. 관리자에게 문의 하시기 바랍니다.");
        }
    }

    /**
     * 토큰이 잘못 되었을 때 발생하는 Exception을 처리함
     */
    private void handleInvalidAuthTokenException(HttpServletResponse response, InvalidAuthTokenException e)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(new MessageVo(HttpStatus.CONFLICT,
                "message", e.getMessage())));
    }
}
