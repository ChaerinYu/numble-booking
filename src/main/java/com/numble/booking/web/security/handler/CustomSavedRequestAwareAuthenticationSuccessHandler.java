package com.numble.booking.web.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numble.booking.user.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * <pre>
 * Class Name : CustomSavedRequestAwareAuthenticationSuccessHandler
 * Description : 로그인 성공 Handler
 * - SecurityContext에 인증정보를 set
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-02-01	    user	New
 * </pre>
 *
 * @author user
 * @since 2024-02-01
 */
public class CustomSavedRequestAwareAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private ObjectMapper objectMapper;

    private static final String USERNAMES = "username";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        // 사용자의 정보를 꺼낼 경우, SecurityContextHolder의 context에서 조회
        SecurityContextHolder.getContext().setAuthentication(authentication);

        userLoginService.handleLoginSuccessUser(authentication.getName());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(Map.of("token", request.getAttribute("token"))));
        response.getWriter().flush();
    }
}
