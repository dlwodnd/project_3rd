package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHotelFavoritesEntity is a Querydsl query type for HotelFavoritesEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHotelFavoritesEntity extends EntityPathBase<HotelFavoritesEntity> {

    private static final long serialVersionUID = 1746318019L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHotelFavoritesEntity hotelFavoritesEntity = new QHotelFavoritesEntity("hotelFavoritesEntity");

    public final com.green.hoteldog.entity.base.QCreatedAtBaseEntity _super = new com.green.hoteldog.entity.base.QCreatedAtBaseEntity(this);

    public final com.green.hoteldog.entity.composite.QHotelFavoritesComposite composite;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final QHotelEntity hotelEntity;

    public final QUserEntity userEntity;

    public QHotelFavoritesEntity(String variable) {
        this(HotelFavoritesEntity.class, forVariable(variable), INITS);
    }

    public QHotelFavoritesEntity(Path<? extends HotelFavoritesEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHotelFavoritesEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHotelFavoritesEntity(PathMetadata metadata, PathInits inits) {
        this(HotelFavoritesEntity.class, metadata, inits);
    }

    public QHotelFavoritesEntity(Class<? extends HotelFavoritesEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.composite = inits.isInitialized("composite") ? new com.green.hoteldog.entity.composite.QHotelFavoritesComposite(forProperty("composite")) : null;
        this.hotelEntity = inits.isInitialized("hotelEntity") ? new QHotelEntity(forProperty("hotelEntity"), inits.get("hotelEntity")) : null;
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity")) : null;
    }

}

