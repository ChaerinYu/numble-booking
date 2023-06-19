package com.numble.booking.user.domian;

import com.numble.booking.benefit.domain.Coupon;
import com.numble.booking.user.domian.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * <pre>
 * Class Name : UserCoupon
 * Description : 사용자가 갖고 있는 쿠폰
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
public class UserCoupon {

    @Id
    @Column(name = "userCouponId", nullable = false)
    @GeneratedValue(generator = "userCouponSeqGenerator")
    @GenericGenerator(name = "userCouponSeqGenerator", strategy = "com.numble.booking.util.SeqGenerator")
    private Long id;

    // 쿠폰 갖고 있는 사용자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    // 쿠폰
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "couponId", nullable = false)
    private Coupon coupon;

}
