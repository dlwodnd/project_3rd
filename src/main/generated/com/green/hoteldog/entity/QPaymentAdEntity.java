package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPaymentAdEntity is a Querydsl query type for PaymentAdEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaymentAdEntity extends EntityPathBase<PaymentAdEntity> {

    private static final long serialVersionUID = 928423407L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPaymentAdEntity paymentAdEntity = new QPaymentAdEntity("paymentAdEntity");

    public final com.green.hoteldog.entity.base.QCreatedAtBaseEntity _super = new com.green.hoteldog.entity.base.QCreatedAtBaseEntity(this);

    public final QBusinessEntity businessEntity;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final QHotelAdvertiseEntity hotelAdvertiseEntity;

    public final StringPath paymentAdNum = createString("paymentAdNum");

    public final DateTimePath<java.time.LocalDateTime> paymentDate = createDateTime("paymentDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> paymentPk = createNumber("paymentPk", Long.class);

    public final NumberPath<Long> paymentStatus = createNumber("paymentStatus", Long.class);

    public QPaymentAdEntity(String variable) {
        this(PaymentAdEntity.class, forVariable(variable), INITS);
    }

    public QPaymentAdEntity(Path<? extends PaymentAdEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPaymentAdEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPaymentAdEntity(PathMetadata metadata, PathInits inits) {
        this(PaymentAdEntity.class, metadata, inits);
    }

    public QPaymentAdEntity(Class<? extends PaymentAdEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.businessEntity = inits.isInitialized("businessEntity") ? new QBusinessEntity(forProperty("businessEntity"), inits.get("businessEntity")) : null;
        this.hotelAdvertiseEntity = inits.isInitialized("hotelAdvertiseEntity") ? new QHotelAdvertiseEntity(forProperty("hotelAdvertiseEntity"), inits.get("hotelAdvertiseEntity")) : null;
    }

}

