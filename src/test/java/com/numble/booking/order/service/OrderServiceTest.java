package com.numble.booking.order.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithUserDetails;

import com.numble.booking.annotation.BookingTest;
import com.numble.booking.order.domain.Order;
import com.numble.booking.order.exception.BadRequestOrderException;
import com.numble.booking.order.type.OrderStatus;
import com.numble.booking.order.value.OrderDetailVo;
import com.numble.booking.order.value.OrderFindDto;
import com.numble.booking.order.value.OrderListVo;
import com.numble.booking.order.value.OrderStatusModifyDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <pre>
 * Class Name : OrderServiceTest
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
@BookingTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("주문 목록 조회")
    void findAll() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 3);
        OrderFindDto dto = new OrderFindDto();
        dto.setFromDate(LocalDate.of(2024, 1, 1));
        dto.setToDate(LocalDate.of(2024, 1, 10));
        // when
        Page<OrderListVo> all = orderService.findAll(pageRequest, dto);
        // then
        List<OrderListVo> content = all.getContent();
        assertThat(content).isNotEmpty();
        assertThat(content.get(0).getOrderItems()).isNotEmpty();
        assertThat(content.get(0).getOrderStatus()).isEqualTo(OrderStatus.APPROVED);
    }

    @Test
    @DisplayName("주문 상세 조회")
    void find() {
        // given
        Long orderId = 1002L;
        // when
        OrderDetailVo vo = orderService.find(orderId);
        // then
        assertThat(vo).isNotNull();
        assertThat(vo.getDeliveryDetail().getFullAddress()).isEqualTo("(13355) 경기도 수원시");
        assertThat(vo.getOrderItems()).isNotNull();
        assertThat(vo.getOrderItems().get(0).getTicketDetail()).isNotNull();
        assertThat(vo.getOrderItems().get(0).getTicketDetail().getTicketKey()).isEqualTo("A111111111");
        assertThat(vo.getOrderStatus()).isEqualTo(OrderStatus.CANCELED);
        assertThat(vo.getTotalPrice()).isEqualTo(30000);
    }
    
    @Test
    @WithUserDetails("USER1")
    @DisplayName("구매 확정")
    void modifyStatusConfirmPurchase() {
        // given
        Long orderId = 1000L;
        OrderStatusModifyDto dto = new OrderStatusModifyDto();
        dto.setOrderId(orderId);
        dto.setOrderStatus(OrderStatus.CONFIRM_PURCHASE);
        // when
        Long modifyOrderId = orderService.modifyStatus(dto);
        // then
        Order order = em.find(Order.class, modifyOrderId);
        assertThat(order.getId()).isEqualTo(orderId);
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.CONFIRM_PURCHASE);
    }
    
    @Test
    @WithUserDetails("USER1")
    @DisplayName("구매 확정 오류")
    void modifyStatusConfirmPurchaseError() {
        // given
        Long orderId = 1001L;
        OrderStatusModifyDto dto = new OrderStatusModifyDto();
        dto.setOrderId(orderId);
        dto.setOrderStatus(OrderStatus.CONFIRM_PURCHASE);
        // when, then
        BadRequestOrderException exception = assertThrows(BadRequestOrderException.class, () -> orderService.modifyStatus(dto));
        assertThat(exception.getMessage()).isEqualTo("배송이 완료되지 않은 주문입니다.");
    }
    
    @Test
    @WithUserDetails("USER2")
    @DisplayName("구매 확정 오류 - 다른 사용자")
    void modifyStatusOtherUserError() {
        // given
        Long orderId = 1000L;
        OrderStatusModifyDto dto = new OrderStatusModifyDto();
        dto.setOrderId(orderId);
        dto.setOrderStatus(OrderStatus.CONFIRM_PURCHASE);
        // when, then
        BadRequestOrderException exception = assertThrows(BadRequestOrderException.class, () -> orderService.modifyStatus(dto));
        assertThat(exception.getMessage()).isEqualTo("잘못된 요청입니다.");
    }
}