package com.green.hoteldog.common.repository;

import com.green.hoteldog.common.entity.BusinessEntity;
import com.green.hoteldog.common.entity.UserEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BusinessRepository extends JpaRepository<BusinessEntity, Long> {

    //승민
    List<BusinessEntity> findByAccountStatus(int accountStatus);

    List<BusinessEntity> findByAccountStatusNot(int accountStatus);

    //승민
    List<BusinessEntity> findByUserEntity_UserPkIn(List<Long> userPks);

//    @Modifying
//    @Query("UPDATE BusinessEntity b SET b.accountStatus = :accountStatus WHERE b.userEntity.userPk = :userPk")
//    void updateBusinessEntityByAccountStatus(@Param("accountStatus") int accountStatus, @Param("userPk") long userPk);

    //비지니스 유저로 바꾸는거
    @Modifying
    @Query("UPDATE BusinessEntity b SET b.accountStatus = :accountStatus WHERE b.businessPk = :businessPk")
    void updateBusinessEntityByAccountStatus(int accountStatus, long businessPk);


      //승준
    //승준

    //영웅
    //영웅

    //재웅
    Optional<BusinessEntity> findByUserEntity(UserEntity userEntity);
    //재웅
}
