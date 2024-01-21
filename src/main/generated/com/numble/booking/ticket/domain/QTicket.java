package com.numble.booking.ticket.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTicket is a Querydsl query type for Ticket
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTicket extends EntityPathBase<Ticket> {

    private static final long serialVersionUID = -1696802827L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTicket ticket = new QTicket("ticket");

    public final com.numble.booking.order.domain.QOrderItem _super;

    //inherited
    public final NumberPath<Integer> count;

    //inherited
    public final NumberPath<Long> createdBy;

    //inherited
    public final DatePath<java.time.LocalDate> createdDate;

    //inherited
    public final TimePath<java.time.LocalTime> createdTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final NumberPath<Long> lastModifiedBy;

    //inherited
    public final DatePath<java.time.LocalDate> lastModifiedDate;

    //inherited
    public final TimePath<java.time.LocalTime> lastModifiedTime;

    // inherited
    public final com.numble.booking.order.domain.QOrder order;

    //inherited
    public final NumberPath<Integer> orderPrice;

    public final com.numble.booking.performance.domain.QPerformanceSeat performanceSeat;

    public final EnumPath<com.numble.booking.ticket.type.TicketStatus> status = createEnum("status", com.numble.booking.ticket.type.TicketStatus.class);

    public final StringPath ticketKey = createString("ticketKey");

    public final com.numble.booking.user.domian.QUser user;

    public QTicket(String variable) {
        this(Ticket.class, forVariable(variable), INITS);
    }

    public QTicket(Path<? extends Ticket> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTicket(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTicket(PathMetadata metadata, PathInits inits) {
        this(Ticket.class, metadata, inits);
    }

    public QTicket(Class<? extends Ticket> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.numble.booking.order.domain.QOrderItem(type, metadata, inits);
        this.count = _super.count;
        this.createdBy = _super.createdBy;
        this.createdDate = _super.createdDate;
        this.createdTime = _super.createdTime;
        this.lastModifiedBy = _super.lastModifiedBy;
        this.lastModifiedDate = _super.lastModifiedDate;
        this.lastModifiedTime = _super.lastModifiedTime;
        this.order = _super.order;
        this.orderPrice = _super.orderPrice;
        this.performanceSeat = inits.isInitialized("performanceSeat") ? new com.numble.booking.performance.domain.QPerformanceSeat(forProperty("performanceSeat"), inits.get("performanceSeat")) : null;
        this.user = inits.isInitialized("user") ? new com.numble.booking.user.domian.QUser(forProperty("user")) : null;
    }

}

