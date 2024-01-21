package com.numble.booking.order.domain;

import com.numble.booking.common.base.CreatedAndModifiedBase;
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
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // 싱글 테이블 전략 설정
//@DiscriminatorColumn(name = "dtype")  // 구분컬럼명 지정
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends CreatedAndModifiedBase {
    @Id
    @Column(name = "orderItemId", nullable = false)
    @GeneratedValue(generator = "orderItemSeqGenerator")
    @GenericGenerator(name = "orderItemSeqGenerator", strategy = "com.numble.booking.util.SeqGenerator")
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "orderId")
    protected Order order;

    protected int orderPrice;  // 주문 가격

    protected int count;  // 주문 수량

    /**
     * 생성
     */
    public static OrderItem create(Order order, int price, int count) {
        OrderItem entity = new OrderItem();
        entity.order = order;
        entity.orderPrice = price;
        entity.count = count;
        return entity;
    }
}
