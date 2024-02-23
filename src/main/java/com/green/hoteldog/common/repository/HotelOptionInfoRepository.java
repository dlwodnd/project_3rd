package com.green.hoteldog.common.repository;

import com.green.hoteldog.common.entity.HotelEntity;
import com.green.hoteldog.common.entity.HotelOptionInfoEntity;
import com.green.hoteldog.common.entity.composite.HotelOptionComposite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelOptionInfoRepository extends JpaRepository<HotelOptionInfoEntity, HotelOptionComposite> ,HotelOptionQDslRepository{

    //승민
    //승민

    //승준
    //승준

    //영웅
    //영웅

    //재웅
    List<HotelOptionInfoEntity> findAllByHotelEntity(HotelEntity hotelEntity);
    //재웅
}
