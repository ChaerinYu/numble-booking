package com.numble.booking.payment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPaymentInfo is a Querydsl query type for PaymentInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaymentInfo extends EntityPathBase<PaymentInfo> {

    private static final long serialVersionUID = 1426246003L;

    public static final QPaymentInfo paymentInfo = new QPaymentInfo("paymentInfo");

    public final EnumPath<com.numble.booking.payment.type.Bank> bank = createEnum("bank", com.numble.booking.payment.type.Bank.class);

    public final NumberPath<Integer> cardCVV = createNumber("cardCVV", Integer.class);

    public final StringPath cardExpiration = createString("cardExpiration");

    public final StringPath cardNumber = createString("cardNumber");

    public final NumberPath<Integer> deliveryPrice = createNumber("deliveryPrice", Integer.class);

    public final EnumPath<com.numble.booking.common.type.YN> eWalletYn = createEnum("eWalletYn", com.numble.booking.common.type.YN.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.numble.booking.payment.type.PaymentMethod> paymentMethod = createEnum("paymentMethod", com.numble.booking.payment.type.PaymentMethod.class);

    public final NumberPath<Integer> usedPoint = createNumber("usedPoint", Integer.class);

    public QPaymentInfo(String variable) {
        super(PaymentInfo.class, forVariable(variable));
    }

    public QPaymentInfo(Path<? extends PaymentInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPaymentInfo(PathMetadata metadata) {
        super(PaymentInfo.class, metadata);
    }

}

