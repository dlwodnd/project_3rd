package com.green.hoteldog.common.entity;

import com.green.hoteldog.common.entity.composite.HotelOptionComposite;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_hotel_option")
public class HotelOptionInfoEntity {
    @EmbeddedId
    private HotelOptionComposite composite;

    @ManyToOne(optional = false,cascade = CascadeType.REMOVE)
    @MapsId("hotelPk")
    @JoinColumn(name = "hotel_pk",columnDefinition = "BIGINT UNSIGNED")
    private HotelEntity hotelEntity;

    @ManyToOne(optional = false,cascade = CascadeType.REMOVE)
    @MapsId("optionPk")
    @JoinColumn(name = "option_pk",columnDefinition = "BIGINT UNSIGNED")
    private HotelOptionEntity userEntity;
}
