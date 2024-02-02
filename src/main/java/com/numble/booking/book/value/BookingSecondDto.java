package com.numble.booking.book.value;

import com.numble.booking.delivery.value.DeliveryCreateDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <pre>
 * Class Name : BookingSecondDto
 * Description : 공연/좌석 선택 후, 결제 DTO
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
@ApiModel("공연/좌석 선택 후, 결제 DTO")
@Getter
@Setter
@NoArgsConstructor
public class BookingSecondDto {

    @ApiModelProperty(value = "공연 id", required = true)
    @NotNull(message = "공연을 선택해 주세요.")
    private Long performanceId;

    @ApiModelProperty(value = "배송 정보")
    @Valid
    private DeliveryCreateDto deliveryDto;

    @ApiModelProperty(value = "로그인한 사용자 id", required = true)
    private Long userId;
}
