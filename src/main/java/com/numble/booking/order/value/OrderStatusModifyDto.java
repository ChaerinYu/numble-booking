package com.numble.booking.order.value;

import javax.validation.constraints.NotNull;

import com.numble.booking.order.type.OrderStatus;
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
@Getter
@Setter
@NoArgsConstructor
public class OrderStatusModifyDto {

    @NotNull(message = "주문 ID가 유효하지 않습니다.")
    @ApiModelProperty(value = "order id", required = true)
    private Long orderId;

    @NotNull(message = "상태 값이 유효하지 않습니다.")
    @ApiModelProperty(value = "상태값", required = true)
    private OrderStatus orderStatus;
}
