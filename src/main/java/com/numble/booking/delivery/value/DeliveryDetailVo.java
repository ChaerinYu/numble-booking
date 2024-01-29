package com.numble.booking.delivery.value;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * Class Name : DeliveryDetailVo
 * Description : 배송 상세 내역
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-01-29 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2024-01-29
 */
@Getter
@NoArgsConstructor
public class DeliveryDetailVo {

    @ApiModelProperty(value = "주소", example = "(12345) 서울시 마포구 합정동")
    private String fullAddress;

    @ApiModelProperty(value = "수령인")
    private String receiverName;

    @ApiModelProperty(value = "휴대번호")
    private String phone;

    @ApiModelProperty(value = "메세지")
    private String message;

    @Builder
    public DeliveryDetailVo(String fullAddress, String receiverName, String phone, String message) {
        this.fullAddress = fullAddress;
        this.receiverName = receiverName;
        this.phone = phone;
        this.message = message;
    }
}
