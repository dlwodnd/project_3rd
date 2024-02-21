package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHotelRoomInfoEntity is a Querydsl query type for HotelRoomInfoEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHotelRoomInfoEntity extends EntityPathBase<HotelRoomInfoEntity> {

    private static final long serialVersionUID = -965340509L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHotelRoomInfoEntity hotelRoomInfoEntity = new QHotelRoomInfoEntity("hotelRoomInfoEntity");

    public final com.green.hoteldog.entity.base.QBaseEntity _super = new com.green.hoteldog.entity.base.QBaseEntity(this);

    public final NumberPath<Long> bookAble = createNumber("bookAble", Long.class);

    public final StringPath cancelReason = createString("cancelReason");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath discountPer = createString("discountPer");

    public final NumberPath<Long> discountSignStatus = createNumber("discountSignStatus", Long.class);

    public final QDogSizeEntity dogSizeEntity;

    public final QHotelEntity hotelEntity;

    public final NumberPath<Long> hotelRoomCost = createNumber("hotelRoomCost", Long.class);

    public final NumberPath<Long> hotelRoomEa = createNumber("hotelRoomEa", Long.class);

    public final StringPath hotelRoomNm = createString("hotelRoomNm");

    public final NumberPath<Long> hotelRoomPk = createNumber("hotelRoomPk", Long.class);

    public final NumberPath<Long> maximum = createNumber("maximum", Long.class);

    public final StringPath roomPic = createString("roomPic");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QHotelRoomInfoEntity(String variable) {
        this(HotelRoomInfoEntity.class, forVariable(variable), INITS);
    }

    public QHotelRoomInfoEntity(Path<? extends HotelRoomInfoEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHotelRoomInfoEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHotelRoomInfoEntity(PathMetadata metadata, PathInits inits) {
        this(HotelRoomInfoEntity.class, metadata, inits);
    }

    public QHotelRoomInfoEntity(Class<? extends HotelRoomInfoEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dogSizeEntity = inits.isInitialized("dogSizeEntity") ? new QDogSizeEntity(forProperty("dogSizeEntity")) : null;
        this.hotelEntity = inits.isInitialized("hotelEntity") ? new QHotelEntity(forProperty("hotelEntity"), inits.get("hotelEntity")) : null;
    }

}

