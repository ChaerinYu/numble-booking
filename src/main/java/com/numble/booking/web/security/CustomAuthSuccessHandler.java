package com.numble.booking.web.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.numble.booking.user.service.UserLoginService;
import com.numble.booking.user.type.UserStatus;
import com.numble.booking.user.value.UserVo;
import org.json.JSONObject;

/**
 * <pre>
 * Class Name : CustomAuthSuccessHandler
 * Description : 사용자의 ‘인증’에 대해 성공하였을 경우 수행되는 Handler로 성공에 대한 사용자에게 반환 값을 구성하여 전달하는 클래스
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
@Configuration
public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UserLoginService userLoginService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        // 사용자 정보 조회
        CustomUser customUser = (CustomUser) authentication.getPrincipal();

        HashMap<String, Object> responseMap = new HashMap<>();
        UserVo userVo = customUser.getUserVo();
        if (UserStatus.ACTIVE.equals(userVo.getStatus())) {
            responseMap.put("contents", userVo);
            responseMap.put("httpStatus", 200);
            responseMap.put("message", null);

            // TODO RemoteAddressFinder로 로그인 ip 기록 남기기
            userLoginService.handleLoginSuccess(userVo.getId());
        } else {
            responseMap.put("contents", null);
            responseMap.put("httpStatus", 400);
            responseMap.put("message", "로그인 실패");
        }
        JSONObject jsonObject = new JSONObject(responseMap);

        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(jsonObject);  // 최종 저장된 '사용자 정보', '사이트 정보' Front 전달
        printWriter.flush();
        printWriter.close();
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
