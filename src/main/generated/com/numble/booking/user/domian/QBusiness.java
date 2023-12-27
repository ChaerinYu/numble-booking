package com.numble.booking.user.domian;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBusiness is a Querydsl query type for Business
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBusiness extends EntityPathBase<Business> {

    private static final long serialVersionUID = 166869592L;

    public static final QBusiness business = new QBusiness("business");

    public final QUser _super = new QUser(this);

    public final StringPath businessLicense = createString("businessLicense");

    //inherited
    public final NumberPath<Long> createdBy = _super.createdBy;

    //inherited
    public final DatePath<java.time.LocalDate> createdDate = _super.createdDate;

    //inherited
    public final TimePath<java.time.LocalTime> createdTime = _super.createdTime;

    //inherited
    public final StringPath email = _super.email;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastLoginDate = _super.lastLoginDate;

    //inherited
    public final NumberPath<Long> lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DatePath<java.time.LocalDate> lastModifiedDate = _super.lastModifiedDate;

    //inherited
    public final TimePath<java.time.LocalTime> lastModifiedTime = _super.lastModifiedTime;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastPasswordModifyDate = _super.lastPasswordModifyDate;

    //inherited
    public final StringPath loginId = _super.loginId;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final StringPath password = _super.password;

    //inherited
    public final EnumPath<com.numble.booking.user.type.UserStatus> status = _super.status;

    public final EnumPath<com.numble.booking.user.type.BusinessType> type = createEnum("type", com.numble.booking.user.type.BusinessType.class);

    public QBusiness(String variable) {
        super(Business.class, forVariable(variable));
    }

    public QBusiness(Path<? extends Business> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBusiness(PathMetadata metadata) {
        super(Business.class, metadata);
    }

}

