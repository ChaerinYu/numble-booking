package com.numble.booking.order.repository;

import static com.numble.booking.order.domain.QOrder.order;
import static com.numble.booking.order.domain.QOrderItem.orderItem;
import static com.numble.booking.user.domian.QUser.user;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.numble.booking.order.value.OrderItemListVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * Class Name : OrderItemQuerydslRepository
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
@Repository
@RequiredArgsConstructor
public class OrderItemQuerydslRepository {
    private final JPAQueryFactory queryFactory;

    public List<OrderItemListVo> findListByOrderIds(List<Long> orderIds) {
        return queryFactory.select(
                Projections.fields(
                        OrderItemListVo.class,
                        orderItem.id.as("orderItemId"),
                        order.id.as("orderId"),
                        orderItem.order.id.as("orderId"),
                        orderItem.orderPrice,
                        orderItem.count,
                        user.id.as("orderUserId"),
                        user.name.as("orderUsername")
                )
        )
                .from(orderItem)
                .innerJoin(orderItem.order, order)
                .innerJoin(orderItem.user, user)
                .where(
                        CollectionUtils.isEmpty(orderIds) ? null : orderItem.order.id.in(orderIds)
                )
                .fetch()
                ;
    }
}
