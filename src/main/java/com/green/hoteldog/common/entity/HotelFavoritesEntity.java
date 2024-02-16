package com.green.hoteldog.common.entity;

import com.green.hoteldog.common.entity.base.CreatedAtBaseEntity;
import com.green.hoteldog.common.entity.composite.HotelFavoritesComposite;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_hotel_favorites")
public class HotelFavoritesEntity extends CreatedAtBaseEntity {
    @EmbeddedId
    private HotelFavoritesComposite composite;

    @ManyToOne(optional = false,cascade = CascadeType.REMOVE)
    @MapsId("hotelPk")
    @JoinColumn(name = "hotel_pk",columnDefinition = "BIGINT UNSIGNED")
    private HotelEntity hotelEntity;

    @ManyToOne(optional = false,cascade = CascadeType.REMOVE)
    @MapsId("userPk")
    @JoinColumn(name = "user_pk",columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userEntity;

}
