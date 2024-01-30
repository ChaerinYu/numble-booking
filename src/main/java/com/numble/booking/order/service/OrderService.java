package com.numble.booking.order.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.numble.booking.delivery.type.DeliveryStatus;
import com.numble.booking.order.domain.Order;
import com.numble.booking.order.exception.BadRequestOrderException;
import com.numble.booking.order.exception.NotFoundOrderException;
import com.numble.booking.order.repository.OrderItemQuerydslRepository;
import com.numble.booking.order.repository.OrderQuerydslRepository;
import com.numble.booking.order.repository.OrderRepository;
import com.numble.booking.order.type.OrderStatus;
import com.numble.booking.order.value.OrderDetailVo;
import com.numble.booking.order.value.OrderFindDto;
import com.numble.booking.order.value.OrderItemListVo;
import com.numble.booking.order.value.OrderListVo;
import com.numble.booking.order.value.OrderStatusModifyDto;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * Class Name : OrderService
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
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderQuerydslRepository orderQuerydslRepository;
    private final OrderItemQuerydslRepository orderItemQuerydslRepository;

    @Transactional(readOnly = true)
    public Page<OrderListVo> findAll(Pageable pageable, OrderFindDto dto) {
        Page<OrderListVo> page = orderQuerydslRepository.findAll(pageable, dto);
        List<OrderListVo> content = page.getContent();
        if (!CollectionUtils.isEmpty(content)) {
            List<Long> orderIds = content.stream().map(OrderListVo::getOrderId).collect(Collectors.toList());
            List<OrderItemListVo> orderItems = orderItemQuerydslRepository.findListByOrderIds(orderIds);
            for (OrderItemListVo orderItem : orderItems) {
                OrderListVo listVo = content.stream().filter(c -> orderItem.getOrderId().equals(c.getOrderId()))
                        .findAny()
                        .orElseThrow(() -> new NotFoundOrderException(orderItem.getOrderId()));
                listVo.addItem(orderItem);
            }
        }
        return page;
    }

    @Transactional(readOnly = true)
    public OrderDetailVo find(Long orderId) {
        OrderDetailVo vo = orderQuerydslRepository.find(orderId);
        if (vo == null) {
            throw new NotFoundOrderException(orderId);
        }
        vo.addItems(orderItemQuerydslRepository.findListByOrderIds(List.of(vo.getOrderId())));
        vo.calculateTotalPrice();

        return vo;
    }

    @Transactional
    public Long modifyStatus(OrderStatusModifyDto dto) {
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(NotFoundOrderException::new);

        validateStatus(dto.getOrderStatus(), order.getDelivery().getStatus());

        order.modifyStatus(dto.getOrderStatus());
        // TODO: 자기 자신의 order 만 수정할 수 있도록 수정
        return order.getId();
    }

    /**
     * 상태 변경 유효성 체크
     * 1. `배송완료`일 때에만 `구매확정`/`교환요청` 가능 -> 배송완료 아닐 때 요청 시 EXCEPTION
     * 2. `구매완료`/`배송완료`일 때에만 `환불요청` 가능 -> 구매완료/배송완료 아닐 때 요청 시 EXCEPTION
     * 3. `구매완료`일 때에만 `취소` 가능 -> 구매완료 아닐 때 요청 시 EXCEPTION
     */
    private void validateStatus(OrderStatus orderStatus, DeliveryStatus deliveryStatus) {
        if (!DeliveryStatus.DONE.equals(deliveryStatus)) {
            if (OrderStatus.CONFIRM_PURCHASE.equals(orderStatus) || OrderStatus.EXCHANGE_REQUEST.equals(orderStatus))
            throw new BadRequestOrderException("배송이 완료되지 않은 주문입니다.");
        }
        if (!DeliveryStatus.PURCHASE.equals(deliveryStatus)) {
            if (OrderStatus.CANCELED.equals(orderStatus)) {
                throw new BadRequestOrderException("취소가 불가능한 주문입니다.");
            }
        }
        if (!DeliveryStatus.DONE.equals(deliveryStatus) && !DeliveryStatus.PURCHASE.equals(deliveryStatus)) {
            if (OrderStatus.REFUND_REQUEST.equals(orderStatus)) {
                throw new BadRequestOrderException("환불 요청이 불가능한 주문입니다.");
            }
        }
    }
}
