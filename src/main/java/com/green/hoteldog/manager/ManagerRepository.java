package com.green.hoteldog.manager;

import com.green.hoteldog.common.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<UserEntity,Long> {

    //모든 유저
    List<UserEntity> findAllByOdrerbycreatedAtdesc();
    // 사업자 유저
    List<UserEntity> findAllByAccountStatusOrderByCreatedAtDesc(Long accountStatus);
    // 일반유저
    List<UserEntity> findAllByAccountStatusNotOrderByCreatedAtDesc(Long accountStatus);

    //List<UserEntity> normalUsers = managerRepository.findAllByAccountStatusNotOrderByCreatedAtDesc(1L);

    //accountStatus  OdrerbycreatedAtdesc
   // List<UserEntity> normalUsers = managerRepository.findAllByAccountStatusOrderByCreatedAtDesc(1L);

}
