package com.numble.booking.price.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPricePolicy is a Querydsl query type for PricePolicy
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPricePolicy extends EntityPathBase<PricePolicy> {

    private static final long serialVersionUID = -1493720681L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPricePolicy pricePolicy = new QPricePolicy("pricePolicy");

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

    public final com.numble.booking.performance.domain.QPerformance performance;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final EnumPath<com.numble.booking.seat.type.SeatType> seatType = createEnum("seatType", com.numble.booking.seat.type.SeatType.class);

    public QPricePolicy(String variable) {
        this(PricePolicy.class, forVariable(variable), INITS);
    }

    public QPricePolicy(Path<? extends PricePolicy> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPricePolicy(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPricePolicy(PathMetadata metadata, PathInits inits) {
        this(PricePolicy.class, metadata, inits);
    }

    public QPricePolicy(Class<? extends PricePolicy> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.performance = inits.isInitialized("performance") ? new com.numble.booking.performance.domain.QPerformance(forProperty("performance"), inits.get("performance")) : null;
    }

}

