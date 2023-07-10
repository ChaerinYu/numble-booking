package com.numble.booking.user.value;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.numble.booking.user.domian.User;
import com.numble.booking.user.type.UserStatus;
import com.numble.booking.util.MapperUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * Class Name : UserVo
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-07-07 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-07-07
 */
@Getter
@Setter
@ApiModel("유저 VO")
public class UserVo {

    @ApiModelProperty(value = "유저 ID")
    private Long id;

    @ApiModelProperty(value = "로그인 ID")
    private String loginId;
    
    @ApiModelProperty(value = "이름")
    private String name;

    @ApiModelProperty(value = "회원 상태", example = "ACTIVE")
    private UserStatus status;

    @ApiModelProperty(value = "마지막 비밀번호 변경일시", example = "2023-07-07 10:00")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm")
    private LocalDateTime lastPasswordModifyDate;

    @ApiModelProperty(value = "마지막 로그인 일시", example = "2023-07-07 10:00")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm")
    private LocalDateTime lastLoginDate;

//    @ApiModelProperty("사용자가 갖고 있는 쿠폰")

    public static UserVo of(User user) {
        return MapperUtil.map(user, UserVo.class);
    }
}
