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

    public final com.numble.booking.order.domain.QOrderItem orderItem;

    public final EnumPath<com.numble.booking.ticket.type.ReceivingMethod> receivingMethod = createEnum("receivingMethod", com.numble.booking.ticket.type.ReceivingMethod.class);

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
        this.orderItem = inits.isInitialized("orderItem") ? new com.numble.booking.order.domain.QOrderItem(forProperty("orderItem"), inits.get("orderItem")) : null;
        this.user = inits.isInitialized("user") ? new com.numble.booking.user.domian.QUser(forProperty("user")) : null;
    }

}

