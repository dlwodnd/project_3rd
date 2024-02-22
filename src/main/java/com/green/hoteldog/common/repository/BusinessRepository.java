package com.green.hoteldog.common.repository;

import com.green.hoteldog.common.entity.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessRepository extends JpaRepository<BusinessEntity, Long> {

    //승민
    List<BusinessEntity> findByAccountStatus(int accountStatus);

    //승준

    //영웅

    //재웅
}
