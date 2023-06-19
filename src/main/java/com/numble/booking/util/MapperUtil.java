package com.numble.booking.util;

import org.modelmapper.ModelMapper;

/**
 * <pre>
 * Class Name : MapperUtil
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
public class MapperUtil {
    private MapperUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> T map(Object source, Class<T> destinationType) {
        ModelMapper mapper = BeanUtil.get(ModelMapper.class);
        return mapper.map(source, destinationType);
    }
}

