package com.numble.booking.order.value;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.numble.booking.delivery.value.DeliveryDetailVo;
import com.numble.booking.order.type.OrderStatus;
import com.numble.booking.ticket.type.ReceivingMethod;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * Class Name : OrderDetailVo
 * Description :
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
public class OrderDetailVo {
    @ApiModelProperty(value = "주문 id")
    private Long orderId;

    @ApiModelProperty(value = "주문자 id")
    private Long orderUserId;

    @ApiModelProperty(value = "주문자 이름")
    private String orderUsername;

    @ApiModelProperty(value = "주문 내역 목록")
    private List<OrderItemListVo> orderItems = new ArrayList<>();
    
    @ApiModelProperty(value = "배송 정보")
    private DeliveryDetailVo deliveryDetail;

    @ApiModelProperty(value = "주문 상태")
    private OrderStatus orderStatus;

    @ApiModelProperty(value = "주문 날짜")
    private LocalDateTime orderDate;

    @ApiModelProperty(value = "티켓 수령 방법")
    private ReceivingMethod receivingMethod;
    
    @ApiModelProperty(value = "총 금액")
    private int totalPrice;

    public void addItems(List<OrderItemListVo> items) {
        for (OrderItemListVo item : items) {
            addItem(item);
        }
    }

    public void addItem(OrderItemListVo item) {
        if (this.orderItems.contains(item)) return;
        this.orderItems.add(item);
    }

    public int calculateTotalPrice() {
        this.totalPrice = orderItems.stream()
                .mapToInt(OrderItemListVo::getOrderPrice)
                .sum();
        return this.totalPrice;
    }
}