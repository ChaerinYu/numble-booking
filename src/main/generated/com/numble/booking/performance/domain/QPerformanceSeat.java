package com.numble.booking.performance.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPerformanceSeat is a Querydsl query type for PerformanceSeat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPerformanceSeat extends EntityPathBase<PerformanceSeat> {

    private static final long serialVersionUID = -2050052470L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPerformanceSeat performanceSeat = new QPerformanceSeat("performanceSeat");

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

    public final StringPath number = createString("number");

    public final QPerformance performance;

    public final com.numble.booking.seat.domain.QSeat seat;

    public final EnumPath<com.numble.booking.seat.type.SeatType> seatType = createEnum("seatType", com.numble.booking.seat.type.SeatType.class);

    public final EnumPath<com.numble.booking.seat.type.SeatStatus> status = createEnum("status", com.numble.booking.seat.type.SeatStatus.class);

    public final com.numble.booking.user.domian.QUser user;

    public QPerformanceSeat(String variable) {
        this(PerformanceSeat.class, forVariable(variable), INITS);
    }

    public QPerformanceSeat(Path<? extends PerformanceSeat> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPerformanceSeat(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPerformanceSeat(PathMetadata metadata, PathInits inits) {
        this(PerformanceSeat.class, metadata, inits);
    }

    public QPerformanceSeat(Class<? extends PerformanceSeat> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.performance = inits.isInitialized("performance") ? new QPerformance(forProperty("performance"), inits.get("performance")) : null;
        this.seat = inits.isInitialized("seat") ? new com.numble.booking.seat.domain.QSeat(forProperty("seat"), inits.get("seat")) : null;
        this.user = inits.isInitialized("user") ? new com.numble.booking.user.domian.QUser(forProperty("user")) : null;
    }

}

