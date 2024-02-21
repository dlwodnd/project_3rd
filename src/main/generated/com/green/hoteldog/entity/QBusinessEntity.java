package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBusinessEntity is a Querydsl query type for BusinessEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBusinessEntity extends EntityPathBase<BusinessEntity> {

    private static final long serialVersionUID = -767213696L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBusinessEntity businessEntity = new QBusinessEntity("businessEntity");

    public final StringPath accountNumber = createString("accountNumber");

    public final NumberPath<Long> accountStatus = createNumber("accountStatus", Long.class);

    public final StringPath bankNm = createString("bankNm");

    public final NumberPath<Long> businessPk = createNumber("businessPk", Long.class);

    public final QUserEntity userEntity;

    public QBusinessEntity(String variable) {
        this(BusinessEntity.class, forVariable(variable), INITS);
    }

    public QBusinessEntity(Path<? extends BusinessEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBusinessEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBusinessEntity(PathMetadata metadata, PathInits inits) {
        this(BusinessEntity.class, metadata, inits);
    }

    public QBusinessEntity(Class<? extends BusinessEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity")) : null;
    }

}

