package com.numble.booking.delivery.domain;

import com.numble.booking.common.base.CreatedAndModifiedBase;
import com.numble.booking.delivery.type.DeliveryStatus;
import com.numble.booking.order.domain.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * <pre>
 * Class Name : Delivery
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
public class Delivery extends CreatedAndModifiedBase {

    @Id
    @Column(name = "deliveryId", nullable = false)
    @GeneratedValue(generator = "deliverySeqGenerator")
    @GenericGenerator(name = "deliverySeqGenerator", strategy = "com.numble.booking.util.SeqGenerator")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    // 수령인
    @Column(nullable = false, length = 50)
    private String receiverName;

    // 휴대폰 번호
    @Column(nullable = false, length = 50)
    private String phone;

    // 배송 메시지
    private String message;
}
