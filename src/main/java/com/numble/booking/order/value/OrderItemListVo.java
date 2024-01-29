package com.numble.booking.order.value;

import com.numble.booking.ticket.type.TicketStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * Class Name : OrderItemListVo
 * Description : 주문 내역
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
public class OrderItemListVo {
    @ApiModelProperty(value = "주문 상품 id")
    private Long orderItemId;

    @ApiModelProperty(value = "주문 id")
    private Long orderId;

    @ApiModelProperty(value = "주문 상품 가격")
    private int orderPrice;

    @ApiModelProperty(value = "주문 상품 갯수")
    private int count;

    @ApiModelProperty(value = "주문자 id")
    private Long orderUserId;
    
    @ApiModelProperty(value = "주문자 명")
    private String orderUsername;

    // TODO: 객체로 따로 빼기 (ticketKey ~ performanceName)
    @ApiModelProperty(value = "티켓 번호")
    private String ticketKey;

    @ApiModelProperty(value = "공연")
    private TicketStatus ticketStatus;

    @ApiModelProperty(value = "좌석")
    private String seatNumber;

    @ApiModelProperty(value = "공연 id")
    private Long performanceId;

    @ApiModelProperty(value = "공연 명")
    private String performanceName;
}
