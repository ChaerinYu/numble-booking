package com.numble.booking.order.domain;

import javax.persistence.*;

import com.numble.booking.common.base.CreatedAndModifiedBase;
import com.numble.booking.delivery.domain.Delivery;
import com.numble.booking.order.exception.BadRequestOrderException;
import com.numble.booking.order.type.OrderStatus;
import com.numble.booking.ticket.type.ReceivingMethod;
import com.numble.booking.user.domian.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Class Name : Order
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-12-26 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-12-26
 */
@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends CreatedAndModifiedBase {

    @Id
    @Column(name = "orderId", nullable = false)
    @GeneratedValue(generator = "orderSeqGenerator")
    @GenericGenerator(name = "orderSeqGenerator", strategy = "com.numble.booking.util.SeqGenerator")
    private Long id;

    // 결제자 / 주문자
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime orderDate;

    // 티켓 수령 방법
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReceivingMethod receivingMethod;


    /**
     * 생성
     */
    public static Order create(Delivery delivery, User user, ReceivingMethod receivingMethod) {
        Order entity = new Order();
        entity.delivery = delivery;
        entity.user = user;
        entity.orderStatus = OrderStatus.PENDING;
        entity.orderDate = LocalDateTime.now();
        entity.receivingMethod = receivingMethod;
        return entity;
    }

    /**
     * 결제 항목 추가
     */
    public void addItems(List<OrderItem> items) {
        items.forEach(this::addItem);
    }

    public void addItem(OrderItem item) {
        boolean duplicated = this.orderItems.stream().anyMatch(i -> i.getId().equals(item.getId()));
        if (duplicated) {
            throw new BadRequestOrderException("중복된 항목이 존재합니다.");
        }
        this.orderItems.add(item);
    }

}
