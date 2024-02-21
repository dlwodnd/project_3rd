package com.green.hoteldog.entity.base;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCreatedAtBaseEntity is a Querydsl query type for CreatedAtBaseEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QCreatedAtBaseEntity extends EntityPathBase<CreatedAtBaseEntity> {

    private static final long serialVersionUID = -186431921L;

    public static final QCreatedAtBaseEntity createdAtBaseEntity = new QCreatedAtBaseEntity("createdAtBaseEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public QCreatedAtBaseEntity(String variable) {
        super(CreatedAtBaseEntity.class, forVariable(variable));
    }

    public QCreatedAtBaseEntity(Path<? extends CreatedAtBaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCreatedAtBaseEntity(PathMetadata metadata) {
        super(CreatedAtBaseEntity.class, metadata);
    }

}

