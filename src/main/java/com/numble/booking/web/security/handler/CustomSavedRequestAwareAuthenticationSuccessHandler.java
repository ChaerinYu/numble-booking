package com.numble.booking.web.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numble.booking.common.base.MessageVo;
import com.numble.booking.user.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 * Class Name : CustomSavedRequestAwareAuthenticationSuccessHandler
 * Description : 로그인 성공 Handler
 * - SecurityContext에 인증정보를 set
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
public class CustomSavedRequestAwareAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        // 사용자의 정보를 꺼낼 경우, SecurityContextHolder의 context에서 조회
        SecurityContextHolder.getContext().setAuthentication(authentication);

        userLoginService.handleLoginSuccessUser(authentication.getName());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(new MessageVo(HttpStatus.OK,
                request.getAttribute("token"), "LOGIN SUCCESS")));
        response.getWriter().flush();
    }
}
