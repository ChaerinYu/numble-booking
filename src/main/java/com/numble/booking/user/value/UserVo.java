package com.numble.booking.user.value;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.numble.booking.user.domian.User;
import com.numble.booking.user.type.RoleType;
import com.numble.booking.user.type.UserStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * Class Name : UserVo
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-01-31 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2024-01-31
 */
@ApiModel("유저 VO")
@Getter
@NoArgsConstructor
public class UserVo {

    @ApiModelProperty("유저 ID")
    private Long id;

    @ApiModelProperty("로그인 ID")
    private String loginId;
    
    @ApiModelProperty("이름")
    private String name;

    @ApiModelProperty(value = "이메일", example = "test@test.com")
    private String email;
    
    @ApiModelProperty(value = "상태", example = "ACTIVE")
    private UserStatus status;

    @ApiModelProperty(value = "권한", example = "MEMBER")
    private RoleType roleType;

    @ApiModelProperty(value = "마지막 비밀번호 변경일시", example = "2024-01-01 00:00")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime lastPasswordModifyDate;

    @ApiModelProperty(value = "마지막 로그인 일시", example = "2024-01-01 00:00")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime lastLoginDate;

    public UserVo(Long id, String loginId, String name, String email, UserStatus status, RoleType roleType,
                  LocalDateTime lastPasswordModifyDate, LocalDateTime lastLoginDate) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        this.email = email;
        this.status = status;
        this.roleType = roleType;
        this.lastPasswordModifyDate = lastPasswordModifyDate;
        this.lastLoginDate = lastLoginDate;
    }

    public UserVo(User user) {
        this(user.getId(), user.getLoginId(), user.getName(), user.getEmail(), user.getStatus(), user.getRoleType(),
                user.getLastPasswordModifyDate(), user.getLastLoginDate());
    }
}
