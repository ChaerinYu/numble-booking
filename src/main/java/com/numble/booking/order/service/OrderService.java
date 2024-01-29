package com.numble.booking.order.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.numble.booking.order.exception.NotFoundOrderException;
import com.numble.booking.order.repository.OrderItemQuerydslRepository;
import com.numble.booking.order.repository.OrderQuerydslRepository;
import com.numble.booking.order.value.OrderDetailVo;
import com.numble.booking.order.value.OrderFindDto;
import com.numble.booking.order.value.OrderItemListVo;
import com.numble.booking.order.value.OrderListVo;
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
}
