package com.numble.booking.payment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDelivery is a Querydsl query type for Delivery
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDelivery extends EntityPathBase<Delivery> {

    private static final long serialVersionUID = 1465411253L;

    public static final QDelivery delivery = new QDelivery("delivery");

    public final com.numble.booking.common.base.QCreatedAndModifiedBase _super = new com.numble.booking.common.base.QCreatedAndModifiedBase(this);

    //inherited
    public final NumberPath<Long> createdBy = _super.createdBy;

    //inherited
    public final DatePath<java.time.LocalDate> createdDate = _super.createdDate;

    //inherited
    public final TimePath<java.time.LocalTime> createdTime = _super.createdTime;

    public final StringPath detailAddress = createString("detailAddress");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final NumberPath<Long> lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DatePath<java.time.LocalDate> lastModifiedDate = _super.lastModifiedDate;

    //inherited
    public final TimePath<java.time.LocalTime> lastModifiedTime = _super.lastModifiedTime;

    public final StringPath mainAddress = createString("mainAddress");

    public final StringPath message = createString("message");

    public final StringPath phone = createString("phone");

    public final StringPath receiverName = createString("receiverName");

    public final NumberPath<Long> zipCode = createNumber("zipCode", Long.class);

    public QDelivery(String variable) {
        super(Delivery.class, forVariable(variable));
    }

    public QDelivery(Path<? extends Delivery> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDelivery(PathMetadata metadata) {
        super(Delivery.class, metadata);
    }

}

