package com.numble.booking.user.value;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.numble.booking.user.domian.User;
import com.numble.booking.user.type.UserStatus;
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
@Getter
@NoArgsConstructor
public class UserVo {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("로그인 ID")
    private String loginId;
    
    @ApiModelProperty("이름")
    private String name;

    @ApiModelProperty(value = "이메일", example = "test@test.com")
    private String email;
    
    @ApiModelProperty(value = "상태", example = "ACTIVE")
    private UserStatus status;
    
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm")
    private LocalDateTime lastPasswordModifyDate;
    
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm")
    private LocalDateTime lastLoginDate;

    public UserVo(Long id, String loginId, String name, String email, UserStatus status,
                  LocalDateTime lastPasswordModifyDate, LocalDateTime lastLoginDate) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        this.email = email;
        this.status = status;
        this.lastPasswordModifyDate = lastPasswordModifyDate;
        this.lastLoginDate = lastLoginDate;
    }

    public UserVo(User user) {
        this(user.getId(), user.getLoginId(), user.getName(), user.getEmail(), user.getStatus(),
                user.getLastPasswordModifyDate(), user.getLastLoginDate());
    }
}
