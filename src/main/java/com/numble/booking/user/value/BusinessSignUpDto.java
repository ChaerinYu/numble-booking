package com.numble.booking.user.value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.numble.booking.user.type.BusinessType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * Class Name : UserSignUpDto
 * Description : 회원가입 DTO
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
public class BusinessSignUpDto extends UserSignUpDto {
    @ApiModelProperty(value = "사업자 번호", notes = "10글자 이하")
    @NotBlank(message = "사업자 번호를 입력해 주세요.")
    @Pattern(regexp = "^\\d{10}$", message = "10자로 입력해 주세요.")
    private String businessLicense;

    @ApiModelProperty(value = "사업자 유형", example = "VENUE_MANAGER/PERFORMANCE_MANAGER")
    @NotNull(message = "사업 유형을 선택해 주세요.")
    private BusinessType type;
}
