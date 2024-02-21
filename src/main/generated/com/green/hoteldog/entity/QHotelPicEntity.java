package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHotelPicEntity is a Querydsl query type for HotelPicEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHotelPicEntity extends EntityPathBase<HotelPicEntity> {

    private static final long serialVersionUID = -886752650L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHotelPicEntity hotelPicEntity = new QHotelPicEntity("hotelPicEntity");

    public final QHotelEntity hotelEntity;

    public final NumberPath<Long> hotelPicPk = createNumber("hotelPicPk", Long.class);

    public final StringPath pic = createString("pic");

    public QHotelPicEntity(String variable) {
        this(HotelPicEntity.class, forVariable(variable), INITS);
    }

    public QHotelPicEntity(Path<? extends HotelPicEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHotelPicEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHotelPicEntity(PathMetadata metadata, PathInits inits) {
        this(HotelPicEntity.class, metadata, inits);
    }

    public QHotelPicEntity(Class<? extends HotelPicEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hotelEntity = inits.isInitialized("hotelEntity") ? new QHotelEntity(forProperty("hotelEntity"), inits.get("hotelEntity")) : null;
    }

}

