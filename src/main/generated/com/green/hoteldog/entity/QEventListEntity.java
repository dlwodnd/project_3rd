package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEventListEntity is a Querydsl query type for EventListEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventListEntity extends EntityPathBase<EventListEntity> {

    private static final long serialVersionUID = -928849858L;

    public static final QEventListEntity eventListEntity = new QEventListEntity("eventListEntity");

    public final NumberPath<Long> eventPk = createNumber("eventPk", Long.class);

    public final StringPath pic = createString("pic");

    public final StringPath url = createString("url");

    public QEventListEntity(String variable) {
        super(EventListEntity.class, forVariable(variable));
    }

    public QEventListEntity(Path<? extends EventListEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEventListEntity(PathMetadata metadata) {
        super(EventListEntity.class, metadata);
    }

}

