package com.green.hoteldog.entity.composite;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHotelOptionComposite is a Querydsl query type for HotelOptionComposite
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QHotelOptionComposite extends BeanPath<HotelOptionComposite> {

    private static final long serialVersionUID = 2127230978L;

    public static final QHotelOptionComposite hotelOptionComposite = new QHotelOptionComposite("hotelOptionComposite");

    public final NumberPath<Long> hotelPk = createNumber("hotelPk", Long.class);

    public final NumberPath<Long> optionPk = createNumber("optionPk", Long.class);

    public QHotelOptionComposite(String variable) {
        super(HotelOptionComposite.class, forVariable(variable));
    }

    public QHotelOptionComposite(Path<? extends HotelOptionComposite> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHotelOptionComposite(PathMetadata metadata) {
        super(HotelOptionComposite.class, metadata);
    }

}

