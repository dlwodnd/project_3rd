package com.green.hoteldog.manager;

import com.green.hoteldog.common.entity.BusinessEntity;
import com.green.hoteldog.common.entity.UserEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<UserEntity,Long> {

    //모든 유저
    List<UserEntity> findAllByOrderByCreatedAtDesc();
    // 사업자 유저
    @Query("SELECT DISTINCT u FROM UserEntity u JOIN u.businessEntities b WHERE b.accountStatus = :accountStatus")
    List<UserEntity> findUsersByBusinessAccountStatus(@Param("accountStatus") Long accountStatus);



    // 일반유저

}
