package com.numble.booking.payment.domain;

import com.numble.booking.common.base.CreatedAndModifiedBase;
import com.numble.booking.user.domian.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * <pre>
 * Class Name : Payment
 * Description : 결제
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-15	    user	New
 * </pre>
 *
 * @author user
 * @since 2023-06-15
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends CreatedAndModifiedBase {

    @Id
    @Column(name = "paymentId", nullable = false)
    @GeneratedValue(generator = "paymentSeqGenerator")
    @GenericGenerator(name = "paymentSeqGenerator", strategy = "com.numble.booking.util.SeqGenerator")
    private Long id;

    // 결제 항목들
    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentItem> paymentItems;
    
    // 결제 정보
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentInfoId")
    private PaymentInfo paymentInfo;

    // 배송지 정보
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deliveryId")
    private Delivery delivery;

    // 결제자 / 주문자
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
}
