package com.green.hoteldog.manager;

import com.green.hoteldog.common.entity.BusinessEntity;
import com.green.hoteldog.common.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<UserEntity,Long> {

    //모든 유저
    List<UserEntity> findAllByOrderByCreatedAtDesc();
    // 사업자 유저
    List<UserEntity> findAllByUserStatusNotOrderByCreatedAtDesc(Long UserStatus);
    // 일반유저
    List<UserEntity> findAllByUserStatusOrderByCreatedAtDesc(Long UserStatus);


}
