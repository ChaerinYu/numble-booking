package com.numble.booking.benefit.domain;

import com.numble.booking.performance.domain.PerformanceCoupon;
import com.numble.booking.user.domian.UserCoupon;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Class Name : Coupon
 * Description : 쿠폰
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
public class Coupon {

    @Id
    @Column(name = "couponId", nullable = false)
    @GeneratedValue(generator = "couponSeqGenerator")
    @GenericGenerator(name = "couponSeqGenerator", strategy = "com.numble.booking.util.SeqGenerator")
    private Long id;

    // 적용되는 공연
    @OneToMany(mappedBy = "coupon", fetch = FetchType.LAZY)
    private List<PerformanceCoupon> performanceCoupons = new ArrayList<>();

    // 쿠폰을 갖고 있는 사용자
    @OneToMany(mappedBy = "coupon", fetch = FetchType.LAZY)
    private List<UserCoupon> userCoupons = new ArrayList<>();
    
    // TODO 쿠폰 유효 기간
}
