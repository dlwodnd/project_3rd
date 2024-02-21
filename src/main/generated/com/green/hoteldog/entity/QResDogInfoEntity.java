package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResDogInfoEntity is a Querydsl query type for ResDogInfoEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResDogInfoEntity extends EntityPathBase<ResDogInfoEntity> {

    private static final long serialVersionUID = -645758902L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResDogInfoEntity resDogInfoEntity = new QResDogInfoEntity("resDogInfoEntity");

    public final com.green.hoteldog.entity.base.QCreatedAtBaseEntity _super = new com.green.hoteldog.entity.base.QCreatedAtBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> dogAge = createNumber("dogAge", Long.class);

    public final StringPath dogNm = createString("dogNm");

    public final QDogSizeEntity dogSizeEntity;

    public final StringPath information = createString("information");

    public final NumberPath<Long> resDogPk = createNumber("resDogPk", Long.class);

    public QResDogInfoEntity(String variable) {
        this(ResDogInfoEntity.class, forVariable(variable), INITS);
    }

    public QResDogInfoEntity(Path<? extends ResDogInfoEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResDogInfoEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResDogInfoEntity(PathMetadata metadata, PathInits inits) {
        this(ResDogInfoEntity.class, metadata, inits);
    }

    public QResDogInfoEntity(Class<? extends ResDogInfoEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dogSizeEntity = inits.isInitialized("dogSizeEntity") ? new QDogSizeEntity(forProperty("dogSizeEntity")) : null;
    }

}

