package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardCategoryEntity is a Querydsl query type for BoardCategoryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardCategoryEntity extends EntityPathBase<BoardCategoryEntity> {

    private static final long serialVersionUID = -100739734L;

    public static final QBoardCategoryEntity boardCategoryEntity = new QBoardCategoryEntity("boardCategoryEntity");

    public final NumberPath<Long> boardCategoryPk = createNumber("boardCategoryPk", Long.class);

    public final StringPath categoryNm = createString("categoryNm");

    public QBoardCategoryEntity(String variable) {
        super(BoardCategoryEntity.class, forVariable(variable));
    }

    public QBoardCategoryEntity(Path<? extends BoardCategoryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardCategoryEntity(PathMetadata metadata) {
        super(BoardCategoryEntity.class, metadata);
    }

}

