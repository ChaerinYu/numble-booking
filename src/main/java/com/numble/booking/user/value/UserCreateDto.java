package com.numble.booking.user.value;

import com.numble.booking.user.type.BusinessType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * Class Name : UserCreateDto
 * Description : 사용자 생성 DTO
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-21 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-06-21
 */
@ApiModel("유저 생성용 DTO")
@Getter
@Setter
@NoArgsConstructor
public class UserCreateDto {

    @ApiModelProperty(value = "비밀번호", notes = "영문, 숫자, 특수문자를 포함하여 8자리 이상 16자 이하")
    private String password;

    @ApiModelProperty(value = "로그인 ID", notes = "12자 이하")
    private String loginId;

    @ApiModelProperty("사용자 이름")
    private String name;

    @ApiModelProperty("사용자 이메일")
    private String email;

    @ApiModelProperty(value = "사업자 번호", notes = "사업자용, 10글자 이하")
    private String businessLicense;

    @ApiModelProperty(value = "사업자 유형", notes = "사업자용", example = "VENUE_MANAGER/PERFORMANCE_MANAGER")
    private BusinessType businessType;

}
