package com.numble.booking.delivery.value;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * <pre>
 * Class Name : DeliveryCreateDto
 * Description : 배송지 입력 DTO
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
@ApiModel("배송 생성 DTO")
@Getter
@Setter
@NoArgsConstructor
public class DeliveryCreateDto {

    @ApiModelProperty(value = "수령인", notes = "수령인 이름은 255자를 넘길 수 없습니다.", required = true)
    @NotBlank(message = "수령인을 입력해 주세요.")
    @Size(max = 255, message = "수령인 이름은 255자를 넘길 수 없습니다.")
    private String receiverName;

    @ApiModelProperty(value = "휴대전화 번호", example = "010-1234-1234", required = true)
    @NotBlank(message = "휴대전화 번호를 입력해 주세요.")
    @Size(max = 20)
    @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "휴대폰 번호 형식이 올바르지 않습니다. (예: 010-1234-1234)")
    private String phone;

    @ApiModelProperty(value = "우편 번호", example = "12345", required = true)
    @NotNull(message = "우편 번호를 입력해 주세요.")
    private Long zipCode;

    @ApiModelProperty(value = "주소", notes = "주소는 최대 255자까지 작성 가능합니다.", required = true)
    @NotBlank(message = "주소를 입력해 주세요.")
    @Size(max = 255, message = "주소는 최대 255자까지 작성 가능합니다.")
    private String mainAddress;

    @ApiModelProperty(value = "상세 주소", notes = "상세 주소는 최대 255자까지 작성 가능합니다.", required = true)
    @NotBlank(message = "상세 주소를 입력해 주세요.")
    @Size(max = 255, message = "상세 주소는 최대 255자까지 작성 가능합니다.")
    private String detailAddress;

    @ApiModelProperty(value = "배송 메시지", notes = "배송 메시지는 최대 255자까지 작성 가능합니다.")
    @Size(max = 255, message = "배송 메시지는 최대 255자까지 작성 가능합니다.")
    private String message;
}
