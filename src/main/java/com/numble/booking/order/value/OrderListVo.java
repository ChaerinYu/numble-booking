package com.numble.booking.order.value;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.numble.booking.order.type.OrderStatus;
import com.numble.booking.ticket.type.ReceivingMethod;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * Class Name : OrderListVo
 * Description : 주문 목록 조회
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
public class OrderListVo {
    @ApiModelProperty(value = "주문 id")
    private Long orderId;

    @ApiModelProperty(value = "주문자 id")
    private Long orderUserId;

    @ApiModelProperty(value = "주문자 이름")
    private String orderUsername;

    @ApiModelProperty(value = "주문 내역 목록")
    private List<OrderItemListVo> orderItems = new ArrayList<>();

    @ApiModelProperty(value = "주소", hidden = true, example = "(12345) 서울시 마포구 합정동")
    private String fullAddress;

    @ApiModelProperty(value = "수령인", hidden = true)
    private String receiverName;

    @ApiModelProperty(value = "휴대번호", hidden = true)
    private String phone;

    @ApiModelProperty(value = "메세지", hidden = true)
    private String message;

    @ApiModelProperty(value = "주문 상태")
    private OrderStatus orderStatus;

    @ApiModelProperty(value = "주문 날짜")
    private LocalDateTime orderDate;

    @ApiModelProperty(value = "티켓 수령 방법")
    private ReceivingMethod receivingMethod;

    public void addItem(OrderItemListVo item) {
        if (this.orderItems.contains(item)) return;
        this.orderItems.add(item);
    }
}
