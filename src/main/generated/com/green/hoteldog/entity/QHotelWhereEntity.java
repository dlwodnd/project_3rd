package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHotelWhereEntity is a Querydsl query type for HotelWhereEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHotelWhereEntity extends EntityPathBase<HotelWhereEntity> {

    private static final long serialVersionUID = -204466733L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHotelWhereEntity hotelWhereEntity = new QHotelWhereEntity("hotelWhereEntity");

    public final StringPath addressName = createString("addressName");

    public final StringPath detailAddress = createString("detailAddress");

    public final QHotelEntity hotelEntity;

    public final StringPath region1DepthName = createString("region1DepthName");

    public final StringPath region2DepthName = createString("region2DepthName");

    public final StringPath region3DepthName = createString("region3DepthName");

    public final StringPath x = createString("x");

    public final StringPath y = createString("y");

    public final StringPath zoneNum = createString("zoneNum");

    public QHotelWhereEntity(String variable) {
        this(HotelWhereEntity.class, forVariable(variable), INITS);
    }

    public QHotelWhereEntity(Path<? extends HotelWhereEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHotelWhereEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHotelWhereEntity(PathMetadata metadata, PathInits inits) {
        this(HotelWhereEntity.class, metadata, inits);
    }

    public QHotelWhereEntity(Class<? extends HotelWhereEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hotelEntity = inits.isInitialized("hotelEntity") ? new QHotelEntity(forProperty("hotelEntity"), inits.get("hotelEntity")) : null;
    }

}

