package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardPicsEntity is a Querydsl query type for BoardPicsEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardPicsEntity extends EntityPathBase<BoardPicsEntity> {

    private static final long serialVersionUID = -1029953323L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardPicsEntity boardPicsEntity = new QBoardPicsEntity("boardPicsEntity");

    public final com.green.hoteldog.entity.base.QCreatedAtBaseEntity _super = new com.green.hoteldog.entity.base.QCreatedAtBaseEntity(this);

    public final QBoardEntity boardEntity;

    public final NumberPath<Long> boardPicPk = createNumber("boardPicPk", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath pic = createString("pic");

    public QBoardPicsEntity(String variable) {
        this(BoardPicsEntity.class, forVariable(variable), INITS);
    }

    public QBoardPicsEntity(Path<? extends BoardPicsEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardPicsEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardPicsEntity(PathMetadata metadata, PathInits inits) {
        this(BoardPicsEntity.class, metadata, inits);
    }

    public QBoardPicsEntity(Class<? extends BoardPicsEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.boardEntity = inits.isInitialized("boardEntity") ? new QBoardEntity(forProperty("boardEntity"), inits.get("boardEntity")) : null;
    }

}

