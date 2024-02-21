package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewFavEntity is a Querydsl query type for ReviewFavEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewFavEntity extends EntityPathBase<ReviewFavEntity> {

    private static final long serialVersionUID = 785628745L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewFavEntity reviewFavEntity = new QReviewFavEntity("reviewFavEntity");

    public final com.green.hoteldog.entity.composite.QReviewFavComposite composite;

    public final QReviewEntity reviewPk;

    public final QUserEntity userPk;

    public QReviewFavEntity(String variable) {
        this(ReviewFavEntity.class, forVariable(variable), INITS);
    }

    public QReviewFavEntity(Path<? extends ReviewFavEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewFavEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewFavEntity(PathMetadata metadata, PathInits inits) {
        this(ReviewFavEntity.class, metadata, inits);
    }

    public QReviewFavEntity(Class<? extends ReviewFavEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.composite = inits.isInitialized("composite") ? new com.green.hoteldog.entity.composite.QReviewFavComposite(forProperty("composite")) : null;
        this.reviewPk = inits.isInitialized("reviewPk") ? new QReviewEntity(forProperty("reviewPk"), inits.get("reviewPk")) : null;
        this.userPk = inits.isInitialized("userPk") ? new QUserEntity(forProperty("userPk")) : null;
    }

}

