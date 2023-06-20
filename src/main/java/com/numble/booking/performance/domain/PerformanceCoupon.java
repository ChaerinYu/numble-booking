package com.numble.booking.performance.domain;

import com.numble.booking.benefit.domain.Coupon;
import com.numble.booking.performance.domain.Performance;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * <pre>
 * Class Name : PerformanceCoupon
 * Description : 공연에서 사용할 수 있는 쿠폰들
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
public class PerformanceCoupon {

    @Id
    @Column(name = "performanceCouponId", nullable = false)
    @GeneratedValue(generator = "performanceCouponSeqGenerator")
    @GenericGenerator(name = "performanceCouponSeqGenerator", strategy = "com.numble.booking.util.SeqGenerator")
    private Long id;

    // 공연
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performanceId", nullable = false)
    private Performance performance;

    // 쿠폰
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "couponId", nullable = false)
    private Coupon coupon;
}
