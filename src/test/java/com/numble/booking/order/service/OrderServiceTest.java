package com.numble.booking.order.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.numble.booking.annotation.BookingTest;
import com.numble.booking.order.type.OrderStatus;
import com.numble.booking.order.value.OrderDetailVo;
import com.numble.booking.order.value.OrderFindDto;
import com.numble.booking.order.value.OrderListVo;
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
        assertThat(vo.getFullAddress()).isEqualTo("(13355) 경기도 수원시");
        assertThat(vo.getOrderItems()).isNotNull();
        assertThat(vo.getOrderStatus()).isEqualTo(OrderStatus.CANCELED);
        assertThat(vo.getTotalPrice()).isEqualTo(30000);
    }
}