package com.numble.booking.user.domian;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 114913891L;

    public static final QUser user = new QUser("user");

    public final com.numble.booking.common.base.QCreatedAndModifiedBase _super = new com.numble.booking.common.base.QCreatedAndModifiedBase(this);

    //inherited
    public final NumberPath<Long> createdBy = _super.createdBy;

    //inherited
    public final DatePath<java.time.LocalDate> createdDate = _super.createdDate;

    //inherited
    public final TimePath<java.time.LocalTime> createdTime = _super.createdTime;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> lastLoginDate = createDateTime("lastLoginDate", java.time.LocalDateTime.class);

    //inherited
    public final NumberPath<Long> lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DatePath<java.time.LocalDate> lastModifiedDate = _super.lastModifiedDate;

    //inherited
    public final TimePath<java.time.LocalTime> lastModifiedTime = _super.lastModifiedTime;

    public final DateTimePath<java.time.LocalDateTime> lastPasswordModifyDate = createDateTime("lastPasswordModifyDate", java.time.LocalDateTime.class);

    public final StringPath loginId = createString("loginId");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final EnumPath<com.numble.booking.user.type.RoleType> roleType = createEnum("roleType", com.numble.booking.user.type.RoleType.class);

    public final EnumPath<com.numble.booking.user.type.UserStatus> status = createEnum("status", com.numble.booking.user.type.UserStatus.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

