package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRefundEntity is a Querydsl query type for RefundEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRefundEntity extends EntityPathBase<RefundEntity> {

    private static final long serialVersionUID = -1963556744L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRefundEntity refundEntity = new QRefundEntity("refundEntity");

    public final StringPath refundAmount = createString("refundAmount");

    public final DateTimePath<java.time.LocalDateTime> refundDate = createDateTime("refundDate", java.time.LocalDateTime.class);

    public final StringPath refundNum = createString("refundNum");

    public final NumberPath<Long> refundPk = createNumber("refundPk", Long.class);

    public final QRefundPolicyEntity refundPolicyEntity;

    public final NumberPath<Long> refundStatus = createNumber("refundStatus", Long.class);

    public final QReservationEntity reservationEntity;

    public final QUserEntity userEntity;

    public QRefundEntity(String variable) {
        this(RefundEntity.class, forVariable(variable), INITS);
    }

    public QRefundEntity(Path<? extends RefundEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRefundEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRefundEntity(PathMetadata metadata, PathInits inits) {
        this(RefundEntity.class, metadata, inits);
    }

    public QRefundEntity(Class<? extends RefundEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.refundPolicyEntity = inits.isInitialized("refundPolicyEntity") ? new QRefundPolicyEntity(forProperty("refundPolicyEntity")) : null;
        this.reservationEntity = inits.isInitialized("reservationEntity") ? new QReservationEntity(forProperty("reservationEntity"), inits.get("reservationEntity")) : null;
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity")) : null;
    }

}

