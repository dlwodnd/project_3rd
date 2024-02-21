package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHotelOptionInfoEntity is a Querydsl query type for HotelOptionInfoEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHotelOptionInfoEntity extends EntityPathBase<HotelOptionInfoEntity> {

    private static final long serialVersionUID = -1837993987L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHotelOptionInfoEntity hotelOptionInfoEntity = new QHotelOptionInfoEntity("hotelOptionInfoEntity");

    public final com.green.hoteldog.entity.composite.QHotelOptionComposite composite;

    public final QHotelEntity hotelEntity;

    public final QHotelOptionEntity userEntity;

    public QHotelOptionInfoEntity(String variable) {
        this(HotelOptionInfoEntity.class, forVariable(variable), INITS);
    }

    public QHotelOptionInfoEntity(Path<? extends HotelOptionInfoEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHotelOptionInfoEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHotelOptionInfoEntity(PathMetadata metadata, PathInits inits) {
        this(HotelOptionInfoEntity.class, metadata, inits);
    }

    public QHotelOptionInfoEntity(Class<? extends HotelOptionInfoEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.composite = inits.isInitialized("composite") ? new com.green.hoteldog.entity.composite.QHotelOptionComposite(forProperty("composite")) : null;
        this.hotelEntity = inits.isInitialized("hotelEntity") ? new QHotelEntity(forProperty("hotelEntity"), inits.get("hotelEntity")) : null;
        this.userEntity = inits.isInitialized("userEntity") ? new QHotelOptionEntity(forProperty("userEntity")) : null;
    }

}

