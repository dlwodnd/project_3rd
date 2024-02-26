package com.green.hoteldog.common.repository;

import com.green.hoteldog.common.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<HotelEntity, Long> {



    // findHotel where BusinessEntity.accountStatus = 1
    List<HotelEntity> findByBusinessEntity_AccountStatus(int accountStatus);
    //승민
    //승민

    //승준
    //승준

    //영웅

    //영웅
    HotelEntity findByHotelPk(long hotelPk);
    //재웅
    //재웅


}
