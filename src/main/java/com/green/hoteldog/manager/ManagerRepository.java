package com.green.hoteldog.manager;

import com.green.hoteldog.entity.BusinessEntity;
import com.green.hoteldog.entity.UserEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<UserEntity,Long> {

    //모든 유저
    List<UserEntity> findAllByOrderByCreatedAtDesc();

    //사업자
    List<UserEntity> findAllByUserPkInOrderByCreatedAtDesc(List<Long> userPks);
   // 일반유저
    List<UserEntity> findAllByUserPkNotInOrderByCreatedAtDesc(List<Long> userPks);

    // 일반유저

}
