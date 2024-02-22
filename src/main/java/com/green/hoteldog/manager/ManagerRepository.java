package com.green.hoteldog.manager;

import com.green.hoteldog.common.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerRepository extends JpaRepository<UserEntity,Long> {

    //모든 유저
    List<UserEntity> findAllByOrderByCreatedAtDesc();

    //사업자
    List<UserEntity> findAllByUserPkInOrderByCreatedAtDesc(List<Long> userPks);
   // 일반유저
    List<UserEntity> findAllByUserPkNotInOrderByCreatedAtDesc(List<Long> userPks);

    // 일반유저

}
