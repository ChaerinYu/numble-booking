package com.numble.booking.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * Class Name : RemoteAddressFinder
 * Description : remote address를 찾는 utility
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-07-12 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-07-12
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RemoteAddressFinder {

    public static String getAddress(HttpServletRequest request) {
        final String UNKNOWN = "unknown";
        final String[] HEADERS = {
                "X-Forwarded-For",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_CLIENT_IP",
                "HTTP_X_FORWARDED_FOR"
        };

        boolean find = false;
        String ip = null;
        for (String header : HEADERS) {
            ip = request.getHeader(header);
            if (StringUtils.hasText(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
                find = true;
                break;
            }
        }

        if (!find) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
