package com.green.hoteldog.entity.composite;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReviewFavComposite is a Querydsl query type for ReviewFavComposite
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QReviewFavComposite extends BeanPath<ReviewFavComposite> {

    private static final long serialVersionUID = -1924751128L;

    public static final QReviewFavComposite reviewFavComposite = new QReviewFavComposite("reviewFavComposite");

    public final NumberPath<Long> reviewPk = createNumber("reviewPk", Long.class);

    public final NumberPath<Long> userPk = createNumber("userPk", Long.class);

    public QReviewFavComposite(String variable) {
        super(ReviewFavComposite.class, forVariable(variable));
    }

    public QReviewFavComposite(Path<? extends ReviewFavComposite> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReviewFavComposite(PathMetadata metadata) {
        super(ReviewFavComposite.class, metadata);
    }

}

