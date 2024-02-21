package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHotelOptionEntity is a Querydsl query type for HotelOptionEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHotelOptionEntity extends EntityPathBase<HotelOptionEntity> {

    private static final long serialVersionUID = 1259708335L;

    public static final QHotelOptionEntity hotelOptionEntity = new QHotelOptionEntity("hotelOptionEntity");

    public final StringPath hotelNm = createString("hotelNm");

    public final NumberPath<Long> hotelPk = createNumber("hotelPk", Long.class);

    public QHotelOptionEntity(String variable) {
        super(HotelOptionEntity.class, forVariable(variable));
    }

    public QHotelOptionEntity(Path<? extends HotelOptionEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHotelOptionEntity(PathMetadata metadata) {
        super(HotelOptionEntity.class, metadata);
    }

}

