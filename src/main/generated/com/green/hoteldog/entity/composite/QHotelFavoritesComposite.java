package com.green.hoteldog.entity.composite;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHotelFavoritesComposite is a Querydsl query type for HotelFavoritesComposite
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QHotelFavoritesComposite extends BeanPath<HotelFavoritesComposite> {

    private static final long serialVersionUID = 1577142656L;

    public static final QHotelFavoritesComposite hotelFavoritesComposite = new QHotelFavoritesComposite("hotelFavoritesComposite");

    public final NumberPath<Long> hotelPk = createNumber("hotelPk", Long.class);

    public final NumberPath<Long> userPk = createNumber("userPk", Long.class);

    public QHotelFavoritesComposite(String variable) {
        super(HotelFavoritesComposite.class, forVariable(variable));
    }

    public QHotelFavoritesComposite(Path<? extends HotelFavoritesComposite> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHotelFavoritesComposite(PathMetadata metadata) {
        super(HotelFavoritesComposite.class, metadata);
    }

}

