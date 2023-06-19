package com.numble.booking.payment.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * <pre>
 * Class Name : PaymentItemInfo
 * Description : 결제 항목 결제 정보
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
public class PaymentItemInfo {

    @Id
    @Column(name = "paymentItemInfoId", nullable = false)
    @GeneratedValue(generator = "paymentItemInfoSeqGenerator")
    @GenericGenerator(name = "paymentItemInfoSeqGenerator", strategy = "com.numble.booking.util.SeqGenerator")
    private Long id;

    // 쿠폰 사용하지 않은 가격
    @Column(nullable = false)
    private Integer orgPrice;

    // 쿠폰 할인
    private Integer usedCouponPrice;
}
