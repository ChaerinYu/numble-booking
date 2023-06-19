package com.numble.booking.util;

import org.springframework.context.ApplicationContext;

/**
 * <pre>
 * Class Name : BeanUtil
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-18	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-18
 */
public class BeanUtil {
    private static ApplicationContext context;

    private BeanUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void init(ApplicationContext context) {
        BeanUtil.context = context;
    }

    public static <T> T get(Class<T> clazz) {
        return context.getBean(clazz);
    }
}
