package com.numble.booking.web.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

/**
 * <pre>
 * Class Name : CustomAuthFailureHandler
 * Description : 사용자의 ‘인증’에 대해 실패하였을 경우 수행되는 Handler로 실패에 대한 사용자에게 반환값을 구성하여 전달하는 클래스
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
@Configuration
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // [STEP1] 클라이언트로 전달 할 응답 값을 구성합니다.
        JSONObject jsonObject = new JSONObject();
        String failMsg = "";

        // [STEP2] 발생한 Exception 에 대해서 확인합니다.
        if (exception instanceof AuthenticationServiceException) {
            failMsg = "로그인 정보가 일치하지 않습니다.";

        } else if (exception instanceof BadCredentialsException) {
            failMsg = "로그인 정보가 일치하지 않습니다.";

        } else if (exception instanceof LockedException) {
            failMsg = "로그인 정보가 일치하지 않습니다.";

        } else if (exception instanceof DisabledException) {
            failMsg = "로그인 정보가 일치하지 않습니다.";

        } else if (exception instanceof AccountExpiredException) {
            failMsg = "로그인 정보가 일치하지 않습니다.";

        } else if (exception instanceof CredentialsExpiredException) {
            failMsg = "로그인 정보가 일치하지 않습니다.";
        }
        // [STEP4] 응답 값을 구성하고 전달합니다.
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();

        log.debug(failMsg);

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("userInfo", null);
        resultMap.put("resultCode", 9999);
        resultMap.put("failMsg", failMsg);
        jsonObject = new JSONObject(resultMap);

        printWriter.print(jsonObject);
        printWriter.flush();
        printWriter.close();
    }
}
