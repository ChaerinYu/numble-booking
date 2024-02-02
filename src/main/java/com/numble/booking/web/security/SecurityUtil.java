package com.numble.booking.web.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.numble.booking.user.value.UserVo;
import com.numble.booking.web.security.domain.CustomUser;

/**
 * <pre>
 * Class Name : SecurityUtil
 * Description : Security Util
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
public class SecurityUtil {

    private SecurityUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static CustomUser getUserInfo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return (CustomUser) principal;
        } else {
            return null;
        }
    }

    public static UserVo getUserVo() {
        return getUserInfo() != null ? getUserInfo().getUserVo() : null;
    }

    public static Long getUserId() {
        return getUserVo() != null ? getUserVo().getId() : null;
    }
}
