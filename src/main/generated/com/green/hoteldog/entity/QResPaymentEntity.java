package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResPaymentEntity is a Querydsl query type for ResPaymentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResPaymentEntity extends EntityPathBase<ResPaymentEntity> {

    private static final long serialVersionUID = 2138629446L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResPaymentEntity resPaymentEntity = new QResPaymentEntity("resPaymentEntity");

    public final QBusinessEntity businessEntity;

    public final StringPath paymentAmount = createString("paymentAmount");

    public final NumberPath<Long> paymentStatus = createNumber("paymentStatus", Long.class);

    public final QReservationEntity reservationEntity;

    public final StringPath resPaymentNum = createString("resPaymentNum");

    public final NumberPath<Long> resPaymentPk = createNumber("resPaymentPk", Long.class);

    public final QUserEntity userEntity;

    public QResPaymentEntity(String variable) {
        this(ResPaymentEntity.class, forVariable(variable), INITS);
    }

    public QResPaymentEntity(Path<? extends ResPaymentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResPaymentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResPaymentEntity(PathMetadata metadata, PathInits inits) {
        this(ResPaymentEntity.class, metadata, inits);
    }

    public QResPaymentEntity(Class<? extends ResPaymentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.businessEntity = inits.isInitialized("businessEntity") ? new QBusinessEntity(forProperty("businessEntity"), inits.get("businessEntity")) : null;
        this.reservationEntity = inits.isInitialized("reservationEntity") ? new QReservationEntity(forProperty("reservationEntity"), inits.get("reservationEntity")) : null;
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity")) : null;
    }

}

