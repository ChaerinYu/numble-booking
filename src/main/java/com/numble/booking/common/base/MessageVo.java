package com.numble.booking.common.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.http.HttpStatus;

/**
 * <pre>
 * Class Name : MessageVo
 * Description : Client 에 보내는 메세지
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-21	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-21
 */
@Getter
@Setter
@NoArgsConstructor
public class MessageVo {
    private Integer httpStatus;
    private Object contents;
    private String message;

    public MessageVo(HttpStatus httpStatus) {
        this.httpStatus = httpStatus.value();
    }

    public MessageVo(HttpStatus httpStatus, Object contents) {
        this.httpStatus = httpStatus.value();
        this.contents = contents;
    }

    public MessageVo(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus.value();
        this.message = message;
    }

    public MessageVo(HttpStatus httpStatus, Object contents, String message) {
        this.httpStatus = httpStatus.value();
        this.contents = contents;
        this.message = message;
    }
}
