package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHotelAdvertiseEntity is a Querydsl query type for HotelAdvertiseEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHotelAdvertiseEntity extends EntityPathBase<HotelAdvertiseEntity> {

    private static final long serialVersionUID = -379136877L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHotelAdvertiseEntity hotelAdvertiseEntity = new QHotelAdvertiseEntity("hotelAdvertiseEntity");

    public final StringPath cancelReason = createString("cancelReason");

    public final DateTimePath<java.time.LocalDateTime> hotelAdvertiseFromDate = createDateTime("hotelAdvertiseFromDate", java.time.LocalDateTime.class);

    public final StringPath hotelAdvertiseNum = createString("hotelAdvertiseNum");

    public final NumberPath<Long> hotelAdvertisePk = createNumber("hotelAdvertisePk", Long.class);

    public final DateTimePath<java.time.LocalDateTime> hotelAdvertiseToDate = createDateTime("hotelAdvertiseToDate", java.time.LocalDateTime.class);

    public final QHotelEntity hotelEntity;

    public final NumberPath<Long> paymentStatus = createNumber("paymentStatus", Long.class);

    public final NumberPath<Long> signStatus = createNumber("signStatus", Long.class);

    public QHotelAdvertiseEntity(String variable) {
        this(HotelAdvertiseEntity.class, forVariable(variable), INITS);
    }

    public QHotelAdvertiseEntity(Path<? extends HotelAdvertiseEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHotelAdvertiseEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHotelAdvertiseEntity(PathMetadata metadata, PathInits inits) {
        this(HotelAdvertiseEntity.class, metadata, inits);
    }

    public QHotelAdvertiseEntity(Class<? extends HotelAdvertiseEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hotelEntity = inits.isInitialized("hotelEntity") ? new QHotelEntity(forProperty("hotelEntity"), inits.get("hotelEntity")) : null;
    }

}

