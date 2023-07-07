package com.numble.booking.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * Class Name : CustomSavedRequestAwareAuthenticationSuccessHandler
 * Description : AuthenticationFilter에서 인증이 성공했을 때 수행될 핸들러
 * 성공 시 인증 토큰을 쿠키에 넣어 주거나 index 페이지로 리다이렉트하는 역할
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
public class CustomSavedRequestAwareAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.debug("CustomSavedRequestAwareAuthenticationSuccessHandler.onAuthenticationSuccess");
        // 쿠키에 인증 토큰
        super.onAuthenticationSuccess(request, response, authentication);
    }
}