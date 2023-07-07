package com.numble.booking.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * Class Name : CustomSimpleUrlAuthenticationFailureHandler
 * Description : login 실패 시 수행되는 핸들러
 * 실패 시 로그인 페이지로 리다이렉트하는 역할
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
public class CustomSimpleUrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.debug("CustomSimpleUrlAuthenticationFailureHandler.onAuthenticationFailure");
        super.onAuthenticationFailure(request, response, exception);
    }
}
