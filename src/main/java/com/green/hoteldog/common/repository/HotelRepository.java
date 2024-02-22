package com.green.hoteldog.common.repository;

import com.green.hoteldog.common.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

    //승민
    //승민

    //승준
    //승준

    //영웅
    HotelEntity findByHotelPk(long hotelPk);
    //영웅

    //재웅
    //재웅
}
