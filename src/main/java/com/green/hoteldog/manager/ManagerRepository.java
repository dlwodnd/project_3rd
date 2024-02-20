package com.green.hoteldog.manager;

import com.green.hoteldog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerRepository extends JpaRepository<UserEntity,Long> {

    //모든 유저
    List<UserEntity> findAllByOrderByCreatedAtDesc();
    // 사업자 유저
    List<UserEntity> findAllByUserStatusNotOrderByCreatedAtDesc(Long UserStatus);
    // 일반유저
    List<UserEntity> findAllByUserStatusOrderByCreatedAtDesc(Long UserStatus);


}
