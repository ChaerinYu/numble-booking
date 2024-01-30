package com.numble.booking.order.repository;

import static com.numble.booking.order.domain.QOrder.order;
import static com.numble.booking.order.domain.QOrderItem.orderItem;
import static com.numble.booking.performance.domain.QPerformance.performance;
import static com.numble.booking.performance.domain.QPerformanceSeat.performanceSeat;
import static com.numble.booking.ticket.domain.QTicket.ticket;
import static com.numble.booking.user.domian.QUser.user;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.numble.booking.order.value.OrderItemListVo;
import com.numble.booking.ticket.value.TicketDetailVo;
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
                        user.name.as("orderUsername"),
                        Projections.fields(TicketDetailVo.class,
                                ticket.ticketKey,
                                ticket.ticketStatus,
                                performanceSeat.seatNumber,
                                performance.id.as("performanceId"),
                                performance.name.as("performanceName")
                        ).as("ticketDetail")
                )
        )
                .from(orderItem)
                .innerJoin(orderItem.order, order)
                .innerJoin(orderItem.user, user)
                .leftJoin(ticket).on(ticket.eq(orderItem))
                .leftJoin(ticket.performanceSeat, performanceSeat)
                .leftJoin(performanceSeat.performance, performance)
                .where(
                        CollectionUtils.isEmpty(orderIds) ? null : orderItem.order.id.in(orderIds)
                )
                .fetch()
                ;
    }
}
