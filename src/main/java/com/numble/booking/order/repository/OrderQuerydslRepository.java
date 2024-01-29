package com.numble.booking.order.repository;

import static com.numble.booking.delivery.domain.QDelivery.delivery;
import static com.numble.booking.order.domain.QOrder.order;
import static com.numble.booking.user.domian.QUser.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.numble.booking.delivery.type.DeliveryStatus;
import com.numble.booking.order.type.OrderStatus;
import com.numble.booking.order.value.OrderDetailVo;
import com.numble.booking.order.value.OrderFindDto;
import com.numble.booking.order.value.OrderListVo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * Class Name : OrderQuerydslRepository
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
public class OrderQuerydslRepository {
    private final JPAQueryFactory queryFactory;

    public Page<OrderListVo> findAll(Pageable pageable, OrderFindDto dto) {
        List<OrderListVo> list = createFindOrdersQuery(dto)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Long> count = createFindOrdersCountQuery(dto);
        return PageableExecutionUtils.getPage(list, pageable, count::fetchOne);
    }

    private JPAQuery<OrderListVo> createFindOrdersQuery(OrderFindDto dto) {
        return queryFactory.select(
                        Projections.fields(
                                OrderListVo.class,
                                order.id.as("orderId"),
                                order.orderStatus,
                                delivery.status.as("deliveryStatus"),
                                order.orderDate,
                                order.receivingMethod,
                                user.id.as("orderUserId"),
                                user.name.as("orderUsername")
                        )
                )
                .from(order)
                .innerJoin(order.delivery, delivery)
                .innerJoin(order.user, user)
                .where(
                        createOrderFindBuilder(dto)
                );
    }

    private JPAQuery<Long> createFindOrdersCountQuery(OrderFindDto dto) {
        return queryFactory.select(order.count())
                .from(order)
                .where(createOrderFindBuilder(dto));
    }

    /**
     * 주문 목록 조회 where
     */
    private BooleanBuilder createOrderFindBuilder(OrderFindDto dto) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(equalUserId(dto.getUserId()));
        builder.and(likeUsername(dto.getUsername()));
        builder.and(equalOrderStatus(dto.getOrderStatus()));
        builder.and(equalDeliveryStatus(dto.getDeliveryStatus()));
        builder.and(goeOrderDate(dto.getFromDate()));
        builder.and(loeOrderDate(dto.getToDate()));
        return builder;
    }

    private BooleanExpression equalUserId(Long userId) {
        return Objects.nonNull(userId) ? order.user.id.eq(userId) : null;
    }

    private BooleanExpression likeUsername(String username) {
        return StringUtils.hasText(username) ? order.user.name.like(username) : null;
    }

    private BooleanExpression equalOrderStatus(OrderStatus status) {
        return Objects.nonNull(status) ? order.orderStatus.eq(status) : null;
    }

    private BooleanExpression equalDeliveryStatus(DeliveryStatus status) {
        return Objects.nonNull(status) ? delivery.status.eq(status) : null;
    }

    private BooleanExpression goeOrderDate(LocalDate fromDate) {
        return Objects.nonNull(fromDate) ? order.orderDate.gt(LocalDateTime.from(fromDate.atStartOfDay().minusDays(1))) : null;
    }

    private BooleanExpression loeOrderDate(LocalDate toDate) {
        return Objects.nonNull(toDate) ? order.orderDate.lt(LocalDateTime.from(toDate.atStartOfDay().plusDays(1))) : null;
    }

    public OrderDetailVo find(Long orderId) {
        return queryFactory.select(
                        Projections.fields(
                                OrderDetailVo.class,
                                order.id.as("orderId"),
                                order.orderStatus,
                                order.orderDate,
                                order.receivingMethod,
                                Expressions.stringTemplate("CONCAT('(', {0}, ')', ' ', {1}, ' ', {2})",
                                                delivery.address.zipCode, delivery.address.mainAddress, delivery.address.detailAddress)
                                        .as("fullAddress"),
                                delivery.receiverName,
                                delivery.phone,
                                delivery.message,
                                user.id.as("orderUserId"),
                                user.name.as("orderUsername")
                        )
                )
                .from(order)
                .innerJoin(order.delivery, delivery)
                .innerJoin(order.user, user)
                .where(
                        orderId != null ? order.id.eq(orderId) : null
                )
                .fetchOne();
    }
}
