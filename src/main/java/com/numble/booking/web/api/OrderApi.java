package com.numble.booking.web.api;

import javax.validation.Valid;

import com.numble.booking.common.base.MessageVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.numble.booking.common.base.AdminUser;
import com.numble.booking.common.base.MemberUser;
import com.numble.booking.order.service.OrderService;
import com.numble.booking.order.value.OrderDetailVo;
import com.numble.booking.order.value.OrderFindDto;
import com.numble.booking.order.value.OrderListVo;
import com.numble.booking.order.value.OrderStatusModifyDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * Class Name : OrderApi
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
@Api(value = "Order APIs")
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderApi {
    private final OrderService orderService;

    @ApiOperation(value = "주문 목록 조회 (사용자)", notes = "일반 사용자 전용")
    @GetMapping
    public Page<OrderListVo> findAllByMember(Pageable pageable, @Validated(MemberUser.class) OrderFindDto dto) {
        return orderService.findAll(pageable, dto);
    }

    @ApiOperation(value = "주문 목록 조회 (시스템 운영자)", notes = "시스템 운영자 전용")
    @GetMapping("/admin")
    public Page<OrderListVo> findAllByAdmin(Pageable pageable, @Validated(AdminUser.class) OrderFindDto dto) {
        return orderService.findAll(pageable, dto);
    }

    @ApiOperation("주문 상세 조회")
    @GetMapping("/{orderId}")
    public OrderDetailVo find(@PathVariable Long orderId) {
        return orderService.find(orderId);
    }

    @ApiOperation(value = "주문 상태 변경", notes = "환불 요청, 교환 요청, 구매 확정")
    @PutMapping("/status")
    public MessageVo modifyStatus(@Valid @RequestBody OrderStatusModifyDto dto) {
        Long orderId = orderService.modifyStatus(dto);
        return new MessageVo(HttpStatus.OK, orderId, "해당 주문 건에 대하여 " + dto.getOrderStatus().getName() + "하였습니다.");
    }
}
