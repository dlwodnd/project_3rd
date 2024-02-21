package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = -1502454325L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final com.green.hoteldog.entity.base.QBaseEntity _super = new com.green.hoteldog.entity.base.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath nickname = createString("nickname");

    public final StringPath phoneNum = createString("phoneNum");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath upw = createString("upw");

    public final StringPath userAddress = createString("userAddress");

    public final StringPath userEmail = createString("userEmail");

    public final StringPath userNum = createString("userNum");

    public final NumberPath<Long> userPk = createNumber("userPk", Long.class);

    public final EnumPath<com.green.hoteldog.entity.jpa_enum.UserRoleEnum> userRole = createEnum("userRole", com.green.hoteldog.entity.jpa_enum.UserRoleEnum.class);

    public final NumberPath<Long> userStatus = createNumber("userStatus", Long.class);

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

