package com.green.hoteldog.entity.composite;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QResComprehensiveInfoComposite is a Querydsl query type for ResComprehensiveInfoComposite
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QResComprehensiveInfoComposite extends BeanPath<ResComprehensiveInfoComposite> {

    private static final long serialVersionUID = 755005953L;

    public static final QResComprehensiveInfoComposite resComprehensiveInfoComposite = new QResComprehensiveInfoComposite("resComprehensiveInfoComposite");

    public final NumberPath<Long> hotelRoomPk = createNumber("hotelRoomPk", Long.class);

    public final NumberPath<Long> resDogPk = createNumber("resDogPk", Long.class);

    public final NumberPath<Long> resPk = createNumber("resPk", Long.class);

    public QResComprehensiveInfoComposite(String variable) {
        super(ResComprehensiveInfoComposite.class, forVariable(variable));
    }

    public QResComprehensiveInfoComposite(Path<? extends ResComprehensiveInfoComposite> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResComprehensiveInfoComposite(PathMetadata metadata) {
        super(ResComprehensiveInfoComposite.class, metadata);
    }

}

