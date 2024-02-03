package com.numble.booking.user.annotation;

import com.numble.booking.user.type.RoleType;

import java.lang.annotation.*;

/**
 * <pre>
 * Class Name : RoleCheck
 * Description : 권한을 확인하는 method에 선언하는 어노테이션
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-02-04	    chaerin	New
 * </pre>
 *
 * @author chaerin
 * @since 2024-02-04
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RoleCheck {

    // 권한
    RoleType[] value() default RoleType.MEMBER;
}
