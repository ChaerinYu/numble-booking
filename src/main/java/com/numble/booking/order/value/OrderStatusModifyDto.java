package com.numble.booking.order.value;

import javax.validation.constraints.NotNull;

import com.numble.booking.order.type.OrderStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * Class Name : OrderStatusModifyDto
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-01-30 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2024-01-30
 */
@ApiModel("주문 목록 수정 DTO")
@Getter
@Setter
@NoArgsConstructor
public class OrderStatusModifyDto {

    @ApiModelProperty(value = "주문 id", required = true)
    @NotNull(message = "주문 ID가 유효하지 않습니다.")
    private Long orderId;

    @ApiModelProperty(value = "주문 상태값", example = "APPROVED", required = true)
    @NotNull(message = "상태 값이 유효하지 않습니다.")
    private OrderStatus orderStatus;
}
