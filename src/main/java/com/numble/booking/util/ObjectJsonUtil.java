package com.numble.booking.util;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * <pre>
 * Class Name : ObjectJsonUtil
 * Description : 객체를 json 혹은 Json 을 객체로 변경
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-07-10 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-07-10
 */
public class ObjectJsonUtil {
    private ObjectJsonUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getJsonStringByObject(Object obj) throws JsonProcessingException {
        return new ObjectMapper()
                .configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, false)
                .configure(MapperFeature.USE_ANNOTATIONS, false)
                .writer()
                .withDefaultPrettyPrinter()
                .writeValueAsString(obj);
    }

    public static Object getObjectByJsonString(String json, Class<?> objectClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeSerializer());
        mapper.registerModule(javaTimeModule);
        return mapper.readValue(json, objectClass);
    }
}
