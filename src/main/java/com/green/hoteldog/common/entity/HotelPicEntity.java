package com.green.hoteldog.common.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_hotel_pic")
public class HotelPicEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelPicPk;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_pk",referencedColumnName = "hotelPk",columnDefinition = "BIGINT UNSIGNED")
    private HotelEntity hotelEntity;
}
