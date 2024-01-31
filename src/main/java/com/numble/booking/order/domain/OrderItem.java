package com.numble.booking.order.domain;

import com.numble.booking.common.base.CreatedAndModifiedBase;
import com.numble.booking.user.domian.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * <pre>
 * Class Name : OrderItem
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-12-27	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-12-27
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends CreatedAndModifiedBase {
    @Id
    @Column(name = "orderItemId", nullable = false)
    @GeneratedValue(generator = "orderItemSeqGenerator")
    @GenericGenerator(name = "orderItemSeqGenerator", strategy = "com.numble.booking.common.util.SeqGenerator")
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "orderId")
    protected Order order;

    // 주문 상품 주인
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false, updatable = false)
    protected User user;

    protected int orderPrice;  // 주문 가격

    protected int count;  // 주문 수량

    /**
     * 생성
     */
    public static OrderItem create(Order order, User user, int price, int count) {
        OrderItem entity = new OrderItem();
        entity.order = order;
        entity.user = user;
        entity.orderPrice = price;
        entity.count = count;
        return entity;
    }
}
