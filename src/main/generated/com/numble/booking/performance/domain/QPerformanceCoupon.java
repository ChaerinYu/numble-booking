package com.numble.booking.performance.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPerformanceCoupon is a Querydsl query type for PerformanceCoupon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPerformanceCoupon extends EntityPathBase<PerformanceCoupon> {

    private static final long serialVersionUID = 841329515L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPerformanceCoupon performanceCoupon = new QPerformanceCoupon("performanceCoupon");

    public final com.numble.booking.benefit.domain.QCoupon coupon;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPerformance performance;

    public QPerformanceCoupon(String variable) {
        this(PerformanceCoupon.class, forVariable(variable), INITS);
    }

    public QPerformanceCoupon(Path<? extends PerformanceCoupon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPerformanceCoupon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPerformanceCoupon(PathMetadata metadata, PathInits inits) {
        this(PerformanceCoupon.class, metadata, inits);
    }

    public QPerformanceCoupon(Class<? extends PerformanceCoupon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.coupon = inits.isInitialized("coupon") ? new com.numble.booking.benefit.domain.QCoupon(forProperty("coupon")) : null;
        this.performance = inits.isInitialized("performance") ? new QPerformance(forProperty("performance"), inits.get("performance")) : null;
    }

}

