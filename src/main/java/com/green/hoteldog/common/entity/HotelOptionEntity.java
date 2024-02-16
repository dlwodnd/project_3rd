package com.green.hoteldog.common.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_hotel_option_info")
public class HotelOptionEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelPk;

    @Column(nullable = false)
    private String hotelNm;
}
