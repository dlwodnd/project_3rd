package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewPicEntity is a Querydsl query type for ReviewPicEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewPicEntity extends EntityPathBase<ReviewPicEntity> {

    private static final long serialVersionUID = 1265833336L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewPicEntity reviewPicEntity = new QReviewPicEntity("reviewPicEntity");

    public final com.green.hoteldog.entity.base.QCreatedAtBaseEntity _super = new com.green.hoteldog.entity.base.QCreatedAtBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath pic = createString("pic");

    public final QReviewEntity reviewEntity;

    public final NumberPath<Long> reviewPicPk = createNumber("reviewPicPk", Long.class);

    public QReviewPicEntity(String variable) {
        this(ReviewPicEntity.class, forVariable(variable), INITS);
    }

    public QReviewPicEntity(Path<? extends ReviewPicEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewPicEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewPicEntity(PathMetadata metadata, PathInits inits) {
        this(ReviewPicEntity.class, metadata, inits);
    }

    public QReviewPicEntity(Class<? extends ReviewPicEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reviewEntity = inits.isInitialized("reviewEntity") ? new QReviewEntity(forProperty("reviewEntity"), inits.get("reviewEntity")) : null;
    }

}

