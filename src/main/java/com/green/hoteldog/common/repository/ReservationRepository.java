package com.green.hoteldog.common.repository;

import com.green.hoteldog.common.entity.HotelEntity;
import com.green.hoteldog.common.entity.ReservationEntity;
import com.green.hoteldog.common.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> ,ReservationQDslRepository{

    //승민
    //승민

    //승준
    //승준

    //영웅
    //영웅

    //재웅
    List<ReservationEntity> findByUserEntityAndResStatus(UserEntity userEntity,long resStatus);
    List<ReservationEntity> findAllByHotelEntityAndResStatusLessThan(HotelEntity hotelEntity,long resStatus);
    //재웅


}
