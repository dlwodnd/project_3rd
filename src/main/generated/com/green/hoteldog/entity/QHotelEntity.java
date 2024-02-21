package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHotelEntity is a Querydsl query type for HotelEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHotelEntity extends EntityPathBase<HotelEntity> {

    private static final long serialVersionUID = -109683686L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHotelEntity hotelEntity = new QHotelEntity("hotelEntity");

    public final com.green.hoteldog.entity.base.QBaseEntity _super = new com.green.hoteldog.entity.base.QBaseEntity(this);

    public final NumberPath<Long> advertise = createNumber("advertise", Long.class);

    public final NumberPath<Long> approval = createNumber("approval", Long.class);

    public final QBusinessEntity businessEntity;

    public final StringPath businessNum = createString("businessNum");

    public final StringPath cancelReason = createString("cancelReason");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath hotelCall = createString("hotelCall");

    public final StringPath hotelDetailInfo = createString("hotelDetailInfo");

    public final StringPath hotelNm = createString("hotelNm");

    public final StringPath hotelNum = createString("hotelNum");

    public final NumberPath<Long> hotelPk = createNumber("hotelPk", Long.class);

    public final NumberPath<Long> signStatus = createNumber("signStatus", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QHotelEntity(String variable) {
        this(HotelEntity.class, forVariable(variable), INITS);
    }

    public QHotelEntity(Path<? extends HotelEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHotelEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHotelEntity(PathMetadata metadata, PathInits inits) {
        this(HotelEntity.class, metadata, inits);
    }

    public QHotelEntity(Class<? extends HotelEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.businessEntity = inits.isInitialized("businessEntity") ? new QBusinessEntity(forProperty("businessEntity"), inits.get("businessEntity")) : null;
    }

}

