package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserDogEntity is a Querydsl query type for UserDogEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserDogEntity extends EntityPathBase<UserDogEntity> {

    private static final long serialVersionUID = 2098779479L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserDogEntity userDogEntity = new QUserDogEntity("userDogEntity");

    public final com.green.hoteldog.entity.base.QBaseEntity _super = new com.green.hoteldog.entity.base.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath dogAge = createString("dogAge");

    public final StringPath dogNm = createString("dogNm");

    public final StringPath dogPic = createString("dogPic");

    public final QDogSizeEntity dogSizeEntity;

    public final StringPath etc = createString("etc");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath userDogNum = createString("userDogNum");

    public final NumberPath<Long> userDogPk = createNumber("userDogPk", Long.class);

    public final QUserEntity userEntity;

    public QUserDogEntity(String variable) {
        this(UserDogEntity.class, forVariable(variable), INITS);
    }

    public QUserDogEntity(Path<? extends UserDogEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserDogEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserDogEntity(PathMetadata metadata, PathInits inits) {
        this(UserDogEntity.class, metadata, inits);
    }

    public QUserDogEntity(Class<? extends UserDogEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dogSizeEntity = inits.isInitialized("dogSizeEntity") ? new QDogSizeEntity(forProperty("dogSizeEntity")) : null;
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity")) : null;
    }

}

