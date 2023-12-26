package com.numble.booking.order.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.numble.booking.common.base.CreatedAndModifiedBase;
import com.numble.booking.user.domian.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
}
