package com.numble.booking.delivery.value;

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
@Getter
@Setter
@NoArgsConstructor
public class DeliveryCreateDto {

    @NotBlank(message = "수령인을 입력해 주세요.")
    @Size(max = 255, message = "수령인 이름은 255자를 넘길 수 없습니다.")
    private String receiverName;

    @NotBlank(message = "휴대전화 번호를 입력해 주세요.")
    @Size(max = 20)
    @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "휴대폰 번호 형식이 올바르지 않습니다. (예: 010-1234-1234)")
    private String phone;

    @NotNull(message = "우편 번호를 입력해 주세요.")
    private Long zipCode;

    @NotBlank(message = "주소를 입력해 주세요.")
    @Size(max = 255, message = "주소는 최대 255자까지 작성 가능합니다.")
    private String mainAddress;

    @NotBlank(message = "상세 주소를 입력해 주세요.")
    @Size(max = 255, message = "상세 주소는 최대 255자까지 작성 가능합니다.")
    private String detailAddress;

    @Size(max = 255, message = "배송 메시지는 최대 255자까지 작성 가능합니다.")
    private String message;
}
