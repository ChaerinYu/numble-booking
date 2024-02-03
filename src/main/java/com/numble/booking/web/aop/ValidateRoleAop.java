package com.numble.booking.web.aop;

import com.numble.booking.user.annotation.RoleCheck;
import com.numble.booking.user.type.RoleType;
import com.numble.booking.user.value.UserVo;
import com.numble.booking.web.exception.RoleException;
import com.numble.booking.web.security.SecurityUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * <pre>
 * Class Name : ValidateRoleAop
 * Description : 권한 검증 AOP
 *
 * Modification Information
 * Modify Date      Modifier    Commentㅑ
 * -------------------------------------------------------------
 * 2024-02-04	    chaerin	New
 * </pre>
 *
 * @author chaerin
 * @since 2024-02-04
 */
@Aspect
@Component
public class ValidateRoleAop {

    @Pointcut("@annotation(roleCheck)")
    public void haveRoleCheck(RoleCheck roleCheck) {}

    @Transactional(readOnly = true)
    @Before(value = "haveRoleCheck(roleCheck)", argNames = "joinPoint,roleCheck")
    public void validateRole(JoinPoint joinPoint, RoleCheck roleCheck) {
        if (!SecurityUtil.isLogin()) {
            throw RoleException.isNonMember();
        }

        UserVo user = SecurityUtil.getUserVo();
        RoleType roleType = user.getRoleType();

        // 권한 없음
        if (roleType == null) {
            throw RoleException.hasNoRole();
        }

        // 권한 없는 경우 (일반 유저인 경우 무시)
        boolean isUserRole = Arrays.asList(roleCheck.value()).contains(RoleType.MEMBER);
        if (!isUserRole && Arrays.stream(roleCheck.value()).noneMatch(r -> r.equals(roleType))) {
            throw RoleException.hasNoRole();
        }
    }
}
