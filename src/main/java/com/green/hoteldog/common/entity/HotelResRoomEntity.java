package com.green.hoteldog.common.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "t_res_room_date")
public class HotelResRoomEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelRoomByDatePk;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_room_pk",referencedColumnName = "hotelRoomPk",columnDefinition = "BIGINT UNSIGNED")
    private HotelRoomInfoEntity hotelRoomInfoEntity;

    @Column(nullable = false,columnDefinition = "BIGINT UNSIGNED")
    private Long roomLeftEa;

    @Column(nullable = false)
    private LocalDate roomDate;

    //승민
    //승민

    //승준
    //승준

    //영웅
    //영웅

    //재웅
    //재웅
}
