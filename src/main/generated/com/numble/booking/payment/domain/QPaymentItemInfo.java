package com.numble.booking.payment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPaymentItemInfo is a Querydsl query type for PaymentItemInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaymentItemInfo extends EntityPathBase<PaymentItemInfo> {

    private static final long serialVersionUID = 1451363494L;

    public static final QPaymentItemInfo paymentItemInfo = new QPaymentItemInfo("paymentItemInfo");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> orgPrice = createNumber("orgPrice", Integer.class);

    public final NumberPath<Integer> usedCouponPrice = createNumber("usedCouponPrice", Integer.class);

    public QPaymentItemInfo(String variable) {
        super(PaymentItemInfo.class, forVariable(variable));
    }

    public QPaymentItemInfo(Path<? extends PaymentItemInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPaymentItemInfo(PathMetadata metadata) {
        super(PaymentItemInfo.class, metadata);
    }

}

