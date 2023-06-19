package com.numble.booking.benefit.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCoupon is a Querydsl query type for Coupon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCoupon extends EntityPathBase<Coupon> {

    private static final long serialVersionUID = 667226104L;

    public static final QCoupon coupon = new QCoupon("coupon");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.numble.booking.performance.domain.PerformanceCoupon, com.numble.booking.performance.domain.QPerformanceCoupon> performanceCoupons = this.<com.numble.booking.performance.domain.PerformanceCoupon, com.numble.booking.performance.domain.QPerformanceCoupon>createList("performanceCoupons", com.numble.booking.performance.domain.PerformanceCoupon.class, com.numble.booking.performance.domain.QPerformanceCoupon.class, PathInits.DIRECT2);

    public final ListPath<com.numble.booking.user.domian.UserCoupon, com.numble.booking.user.domian.QUserCoupon> userCoupons = this.<com.numble.booking.user.domian.UserCoupon, com.numble.booking.user.domian.QUserCoupon>createList("userCoupons", com.numble.booking.user.domian.UserCoupon.class, com.numble.booking.user.domian.QUserCoupon.class, PathInits.DIRECT2);

    public QCoupon(String variable) {
        super(Coupon.class, forVariable(variable));
    }

    public QCoupon(Path<? extends Coupon> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCoupon(PathMetadata metadata) {
        super(Coupon.class, metadata);
    }

}

