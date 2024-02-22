package com.green.hoteldog.common.repository;

import com.green.hoteldog.common.entity.BusinessEntity;
import com.green.hoteldog.common.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusinessRepository extends JpaRepository<BusinessEntity, Long> {

    //승민
    List<BusinessEntity> findByAccountStatus(int accountStatus);
    //승민

    //승준
    //승준

    //영웅
    //영웅

    //재웅
    Optional<BusinessEntity> findByUserEntity(UserEntity userEntity);
    //재웅
}
