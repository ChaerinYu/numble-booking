package com.numble.booking.payment.domain;

import com.numble.booking.common.base.CreatedAndModifiedBase;
import com.numble.booking.performance.domain.PerformanceSeat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * <pre>
 * Class Name : PaymentItem
 * Description : 결제 항목 (결제 공연)
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
public class PaymentItem extends CreatedAndModifiedBase {
    @Id
    @Column(name = "paymentItemId", nullable = false)
    @GeneratedValue(generator = "paymentItemSeqGenerator")
    @GenericGenerator(name = "paymentItemSeqGenerator", strategy = "com.numble.booking.util.SeqGenerator")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentId", nullable = false, updatable = false)
    private Payment payment;

    // 예약 좌석
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performanceSeatId", nullable = false, updatable = false)
    private PerformanceSeat performanceSeat;

    /**
     * 생성
     */
    public static PaymentItem create(Payment payment, PerformanceSeat seat) {
        PaymentItem entity = new PaymentItem();
        entity.payment = payment;
        entity.performanceSeat = seat;
        return entity;
    }
}
