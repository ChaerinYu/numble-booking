package com.numble.booking.venue.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVenue is a Querydsl query type for Venue
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVenue extends EntityPathBase<Venue> {

    private static final long serialVersionUID = -661359163L;

    public static final QVenue venue = new QVenue("venue");

    public final com.numble.booking.common.base.QCreatedAndModifiedBase _super = new com.numble.booking.common.base.QCreatedAndModifiedBase(this);

    public final NumberPath<Long> capacity = createNumber("capacity", Long.class);

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

    public final StringPath name = createString("name");

    public final StringPath possibleTimes = createString("possibleTimes");

    public final ListPath<com.numble.booking.seat.domain.Seat, com.numble.booking.seat.domain.QSeat> seats = this.<com.numble.booking.seat.domain.Seat, com.numble.booking.seat.domain.QSeat>createList("seats", com.numble.booking.seat.domain.Seat.class, com.numble.booking.seat.domain.QSeat.class, PathInits.DIRECT2);

    public final EnumPath<com.numble.booking.venue.type.VenuesType> type = createEnum("type", com.numble.booking.venue.type.VenuesType.class);

    public QVenue(String variable) {
        super(Venue.class, forVariable(variable));
    }

    public QVenue(Path<? extends Venue> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVenue(PathMetadata metadata) {
        super(Venue.class, metadata);
    }

}

