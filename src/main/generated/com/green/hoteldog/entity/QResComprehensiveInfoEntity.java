package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResComprehensiveInfoEntity is a Querydsl query type for ResComprehensiveInfoEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResComprehensiveInfoEntity extends EntityPathBase<ResComprehensiveInfoEntity> {

    private static final long serialVersionUID = 919022370L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResComprehensiveInfoEntity resComprehensiveInfoEntity = new QResComprehensiveInfoEntity("resComprehensiveInfoEntity");

    public final com.green.hoteldog.entity.composite.QResComprehensiveInfoComposite composite;

    public final QHotelRoomInfoEntity hotelRoomInfoEntity;

    public final QResDogInfoEntity resDogInfoEntity;

    public final QReservationEntity reservationEntity;

    public QResComprehensiveInfoEntity(String variable) {
        this(ResComprehensiveInfoEntity.class, forVariable(variable), INITS);
    }

    public QResComprehensiveInfoEntity(Path<? extends ResComprehensiveInfoEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResComprehensiveInfoEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResComprehensiveInfoEntity(PathMetadata metadata, PathInits inits) {
        this(ResComprehensiveInfoEntity.class, metadata, inits);
    }

    public QResComprehensiveInfoEntity(Class<? extends ResComprehensiveInfoEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.composite = inits.isInitialized("composite") ? new com.green.hoteldog.entity.composite.QResComprehensiveInfoComposite(forProperty("composite")) : null;
        this.hotelRoomInfoEntity = inits.isInitialized("hotelRoomInfoEntity") ? new QHotelRoomInfoEntity(forProperty("hotelRoomInfoEntity"), inits.get("hotelRoomInfoEntity")) : null;
        this.resDogInfoEntity = inits.isInitialized("resDogInfoEntity") ? new QResDogInfoEntity(forProperty("resDogInfoEntity"), inits.get("resDogInfoEntity")) : null;
        this.reservationEntity = inits.isInitialized("reservationEntity") ? new QReservationEntity(forProperty("reservationEntity"), inits.get("reservationEntity")) : null;
    }

}

