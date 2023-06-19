package com.numble.booking.common.base;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCreatedAndModifiedBase is a Querydsl query type for CreatedAndModifiedBase
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QCreatedAndModifiedBase extends EntityPathBase<CreatedAndModifiedBase> {

    private static final long serialVersionUID = -1278648060L;

    public static final QCreatedAndModifiedBase createdAndModifiedBase = new QCreatedAndModifiedBase("createdAndModifiedBase");

    public final NumberPath<Long> createdBy = createNumber("createdBy", Long.class);

    public final DatePath<java.time.LocalDate> createdDate = createDate("createdDate", java.time.LocalDate.class);

    public final TimePath<java.time.LocalTime> createdTime = createTime("createdTime", java.time.LocalTime.class);

    public final NumberPath<Long> lastModifiedBy = createNumber("lastModifiedBy", Long.class);

    public final DatePath<java.time.LocalDate> lastModifiedDate = createDate("lastModifiedDate", java.time.LocalDate.class);

    public final TimePath<java.time.LocalTime> lastModifiedTime = createTime("lastModifiedTime", java.time.LocalTime.class);

    public QCreatedAndModifiedBase(String variable) {
        super(CreatedAndModifiedBase.class, forVariable(variable));
    }

    public QCreatedAndModifiedBase(Path<? extends CreatedAndModifiedBase> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCreatedAndModifiedBase(PathMetadata metadata) {
        super(CreatedAndModifiedBase.class, metadata);
    }

}

