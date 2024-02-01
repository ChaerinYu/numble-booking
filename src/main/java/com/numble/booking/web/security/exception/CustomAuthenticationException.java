package com.numble.booking.web.security.exception;

import com.numble.booking.common.type.LoginFailureType;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

/**
 * <pre>
 * Class Name : CustomAuthenticationException
 * Description : Custom 인증 에러
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
@Getter
public class CustomAuthenticationException extends AuthenticationException {
    private final LoginFailureType type;

    public CustomAuthenticationException(LoginFailureType type) {
        super(type.getMessage());
        this.type = type;
    }
}