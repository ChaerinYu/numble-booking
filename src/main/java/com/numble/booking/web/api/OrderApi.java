package com.numble.booking.web.api;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * 주문 목록 조회 (사용자)
     */
    @GetMapping
    public Page<OrderListVo> findAllByMember(Pageable pageable, @Validated(MemberUser.class) OrderFindDto dto) {
        return orderService.findAll(pageable, dto);
    }

    /**
     * 주문 목록 조회 (시스템 운영자)
     */
    @GetMapping("/admin")
    public Page<OrderListVo> findAllByAdmin(Pageable pageable, @Validated(AdminUser.class) OrderFindDto dto) {
        return orderService.findAll(pageable, dto);
    }

    /**
     * 주문 상세 조회
     */
    @GetMapping("/{orderId}")
    public OrderDetailVo find(@PathVariable Long orderId) {
        return orderService.find(orderId);
    }

    /**
     * 환불 요청, 교환 요청, 구매 확정
     */
    @PutMapping("/status")
    public Long modifyStatus(@Valid @RequestBody OrderStatusModifyDto dto) {
        return orderService.modifyStatus(dto);
    }
}
