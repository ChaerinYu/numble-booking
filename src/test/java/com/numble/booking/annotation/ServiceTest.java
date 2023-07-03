package com.numble.booking.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.transaction.Transactional;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.numble.booking.common.type.Profiles;

/**
 * <pre>
 * Class Name : ServiceTest
 * Description : 서비스, Repository 테스트하는 클래스에 선언하는 어노테이션
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-07-03 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-07-03
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest
@Transactional
@ActiveProfiles(Profiles.Constants.TEST)
public @interface ServiceTest {
}
