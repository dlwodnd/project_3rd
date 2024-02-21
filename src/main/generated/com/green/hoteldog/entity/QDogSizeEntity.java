package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDogSizeEntity is a Querydsl query type for DogSizeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDogSizeEntity extends EntityPathBase<DogSizeEntity> {

    private static final long serialVersionUID = -1756887485L;

    public static final QDogSizeEntity dogSizeEntity = new QDogSizeEntity("dogSizeEntity");

    public final NumberPath<Long> dogCost = createNumber("dogCost", Long.class);

    public final StringPath dogSize = createString("dogSize");

    public final NumberPath<Long> sizePk = createNumber("sizePk", Long.class);

    public QDogSizeEntity(String variable) {
        super(DogSizeEntity.class, forVariable(variable));
    }

    public QDogSizeEntity(Path<? extends DogSizeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDogSizeEntity(PathMetadata metadata) {
        super(DogSizeEntity.class, metadata);
    }

}

