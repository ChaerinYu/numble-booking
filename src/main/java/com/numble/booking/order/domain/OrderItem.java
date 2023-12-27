package com.numble.booking.order.domain;

import com.numble.booking.common.base.CreatedAndModifiedBase;
import com.numble.booking.performance.domain.PerformanceSeat;
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
    @GenericGenerator(name = "orderItemSeqGenerator", strategy = "com.numble.booking.util.SeqGenerator")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "performanceSeatId")
    private PerformanceSeat performanceSeat;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    private int orderPrice;  // 주문 가격

    private int count;  // 주문 수량

    /**
     * 생성
     */
    public static OrderItem create(Order order, PerformanceSeat seat) {
        OrderItem entity = new OrderItem();
        entity.order = order;
        entity.performanceSeat = seat;
        return entity;
    }
}
