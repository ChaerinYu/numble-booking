package com.numble.booking.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * <pre>
 * Class Name : CustomLocalDateTimeDeSerializer
 * Description : LocalDateTime DeSerializer
 * "yyyy-MM-dd HH:mm:ss" 형태의 String을 LocalDateTime으로 파싱한다.
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
public class CustomLocalDateTimeDeSerializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return LocalDateTime.parse(p.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
