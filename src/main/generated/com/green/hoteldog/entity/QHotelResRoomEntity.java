package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHotelResRoomEntity is a Querydsl query type for HotelResRoomEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHotelResRoomEntity extends EntityPathBase<HotelResRoomEntity> {

    private static final long serialVersionUID = 1822128455L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHotelResRoomEntity hotelResRoomEntity = new QHotelResRoomEntity("hotelResRoomEntity");

    public final QHotelEntity hotelEntity;

    public final NumberPath<Long> hotelRoomByDatePk = createNumber("hotelRoomByDatePk", Long.class);

    public final StringPath roomDate = createString("roomDate");

    public final NumberPath<Long> roomLeftEa = createNumber("roomLeftEa", Long.class);

    public QHotelResRoomEntity(String variable) {
        this(HotelResRoomEntity.class, forVariable(variable), INITS);
    }

    public QHotelResRoomEntity(Path<? extends HotelResRoomEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHotelResRoomEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHotelResRoomEntity(PathMetadata metadata, PathInits inits) {
        this(HotelResRoomEntity.class, metadata, inits);
    }

    public QHotelResRoomEntity(Class<? extends HotelResRoomEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hotelEntity = inits.isInitialized("hotelEntity") ? new QHotelEntity(forProperty("hotelEntity"), inits.get("hotelEntity")) : null;
    }

}

