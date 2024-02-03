package com.numble.booking.web.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numble.booking.annotation.BookingTest;
import com.numble.booking.order.type.OrderStatus;
import com.numble.booking.order.value.OrderStatusModifyDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.NestedServletException;

/**
 * <pre>
 * Class Name : OrderApiTest
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
@BookingTest
class OrderApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    private final String API = "/orders";


    @Test
    @WithUserDetails("USER1")
    @DisplayName("회원 주문 목록 조회하기 paging")
    void findAllByMember() throws Exception {
        // given
        Long userId = 1001L;
        // when
        ResultActions result = mockMvc.perform(get(API)
                .param("userId", String.valueOf(userId)));
        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].orderId", equalTo(1000)))
                .andExpect(jsonPath("$.content[0].orderItems.size()", equalTo(4)))
                .andExpect(jsonPath("$.content[0].orderItems[0].ticketDetail", notNullValue()))
                .andDo(print());
    }

    @Test
    @WithUserDetails("ADMIN")
    @DisplayName("시스템관리자 주문 목록 조회하기 paging")
    void findAllByAdmin() throws Exception {
        // given
        // when
        ResultActions result = mockMvc.perform(get(API + "/admin")
                .param("orderStatus", OrderStatus.CANCELED.name()));
        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].orderId", equalTo(1002)))
                .andExpect(jsonPath("$.content[0].orderItems.size()", equalTo(1)))
                .andExpect(jsonPath("$.content[0].orderItems[0].ticketDetail", notNullValue()))
                .andDo(print());
    }

    @Test
    @WithUserDetails("USER1")
    @DisplayName("권한 없는 사람이 시스템관리자 주문 목록 조회하기 paging 오류")
    void findAllByAdminFail() {
        NestedServletException exception = assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(get(API + "/admin")
                    .param("orderStatus", OrderStatus.CANCELED.name()));
        });
        assertThat(exception.getCause()).hasMessageContaining("권한이 없습니다.");
    }

    @Test
    @WithUserDetails("USER1")
    @DisplayName("주문 상세 조회")
    void find() throws Exception {
        // given
        Long orderId = 1000L;
        // when
        ResultActions result = mockMvc.perform(get(API + "/" + orderId));
        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId", equalTo(1000)))
                .andDo(print());
    }

    @Test
    @WithUserDetails("USER1")
    @DisplayName("주문 구매확정")
    void modifyStatus() throws Exception {
        // given
        OrderStatusModifyDto dto = new OrderStatusModifyDto();
        dto.setOrderId(1000L);
        dto.setOrderStatus(OrderStatus.CONFIRM_PURCHASE);
        // when
        ResultActions result = mockMvc.perform(put(API + "/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)));
        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.httpStatus", equalTo(200)))
                .andExpect(jsonPath("$.contents", notNullValue()))
                .andExpect(jsonPath("$.message", equalTo("해당 주문 건에 대하여 구매 확정하였습니다.")))
                .andDo(print());
    }
}