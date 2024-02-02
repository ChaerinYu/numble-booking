package com.numble.booking.web.security.dto;

import java.util.HashSet;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.StringUtils;

import lombok.Getter;

/**
 * <pre>
 * Class Name : UserLoginDto
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-02-02 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2024-02-02
 */
@Getter
public class UserLoginDto {
    private String username;
    private String password;

    public UserLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return StringUtils.hasText(username) ? username.toUpperCase() : "";
    }

    public UsernamePasswordAuthenticationToken createAuthRequest() {
        return new UsernamePasswordAuthenticationToken(username, password, new HashSet<>());
    }
}
