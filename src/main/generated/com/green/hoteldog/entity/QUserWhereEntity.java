package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserWhereEntity is a Querydsl query type for UserWhereEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserWhereEntity extends EntityPathBase<UserWhereEntity> {

    private static final long serialVersionUID = -415860030L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserWhereEntity userWhereEntity = new QUserWhereEntity("userWhereEntity");

    public final StringPath addressName = createString("addressName");

    public final StringPath detailAddress = createString("detailAddress");

    public final StringPath region1DepthName = createString("region1DepthName");

    public final StringPath region2DepthName = createString("region2DepthName");

    public final StringPath region3DepthName = createString("region3DepthName");

    public final QUserEntity userEntity;

    public final StringPath x = createString("x");

    public final StringPath y = createString("y");

    public final StringPath zoneNum = createString("zoneNum");

    public QUserWhereEntity(String variable) {
        this(UserWhereEntity.class, forVariable(variable), INITS);
    }

    public QUserWhereEntity(Path<? extends UserWhereEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserWhereEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserWhereEntity(PathMetadata metadata, PathInits inits) {
        this(UserWhereEntity.class, metadata, inits);
    }

    public QUserWhereEntity(Class<? extends UserWhereEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity")) : null;
    }

}

