package com.numble.booking.payment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPaymentItem is a Querydsl query type for PaymentItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaymentItem extends EntityPathBase<PaymentItem> {

    private static final long serialVersionUID = 1426251736L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPaymentItem paymentItem = new QPaymentItem("paymentItem");

    public final com.numble.booking.common.base.QCreatedAndModifiedBase _super = new com.numble.booking.common.base.QCreatedAndModifiedBase(this);

    //inherited
    public final NumberPath<Long> createdBy = _super.createdBy;

    //inherited
    public final DatePath<java.time.LocalDate> createdDate = _super.createdDate;

    //inherited
    public final TimePath<java.time.LocalTime> createdTime = _super.createdTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final NumberPath<Long> lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DatePath<java.time.LocalDate> lastModifiedDate = _super.lastModifiedDate;

    //inherited
    public final TimePath<java.time.LocalTime> lastModifiedTime = _super.lastModifiedTime;

    public final QPayment payment;

    public final QPaymentItemInfo paymentItemInfo;

    public final com.numble.booking.performance.domain.QPerformance performance;

    public QPaymentItem(String variable) {
        this(PaymentItem.class, forVariable(variable), INITS);
    }

    public QPaymentItem(Path<? extends PaymentItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPaymentItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPaymentItem(PathMetadata metadata, PathInits inits) {
        this(PaymentItem.class, metadata, inits);
    }

    public QPaymentItem(Class<? extends PaymentItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.payment = inits.isInitialized("payment") ? new QPayment(forProperty("payment"), inits.get("payment")) : null;
        this.paymentItemInfo = inits.isInitialized("paymentItemInfo") ? new QPaymentItemInfo(forProperty("paymentItemInfo")) : null;
        this.performance = inits.isInitialized("performance") ? new com.numble.booking.performance.domain.QPerformance(forProperty("performance"), inits.get("performance")) : null;
    }

}

