package com.numble.booking.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numble.booking.common.base.MessageVo;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <pre>
 * Class Name : CustomAuthenticationEntryPoint
 * Description : 인가되지 않은 사용자를 로그인 페이지로 보내는 기능
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-02-02	    chaerin 	New
 * </pre>
 *
 * @author user
 * @since 2024-02-02
 */
public class CustomAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    private final ObjectMapper objectMapper;
    /**
     * @param loginFormUrl URL where the login page can be found. Should either be
     *                     relative to the web-app context path (include a leading {@code /}) or an absolute
     *                     URL.
     */
    public CustomAuthenticationEntryPoint(String loginFormUrl, ObjectMapper objectMapper) {
        super(loginFormUrl);
        this.objectMapper = objectMapper;
    }

    /**
     * 인가 실패일 때 JSON요청이면 JSON으로 인가실패 응답값을,
     * 아닐 경우엔 로그인 페이지로 리다이렉트 한다.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=utf-8");
        String json = objectMapper.writeValueAsString(new MessageVo(HttpStatus.UNAUTHORIZED, "잘못된 접근입니다."));
        PrintWriter out = response.getWriter();
        out.print(json);
    }
}
