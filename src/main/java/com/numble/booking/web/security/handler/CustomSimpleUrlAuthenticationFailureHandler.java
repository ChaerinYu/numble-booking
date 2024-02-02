package com.numble.booking.web.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numble.booking.common.base.MessageVo;
import com.numble.booking.common.type.LoginFailureType;
import com.numble.booking.web.security.exception.CustomAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 * Class Name : CustomSimpleUrlAuthenticationFailureHandler
 * Description :
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
public class CustomSimpleUrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        LoginFailureType failureType = LoginFailureType.WRONG_ID_PW;
        if (exception instanceof CustomAuthenticationException) {
            failureType = ((CustomAuthenticationException) exception).getType();
        }

        setResponse(response, failureType);
    }


    /**
     * 실패원인 JSON으로 리턴
     */
    private void setResponse(HttpServletResponse response, LoginFailureType failureType)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(new MessageVo(HttpStatus.UNAUTHORIZED,
                failureType.name(), failureType.getMessage())));
    }
}
