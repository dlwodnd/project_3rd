package com.green.hoteldog.user;

import com.green.hoteldog.common.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserEmail(String email);
    UserEntity findByNickname(String nickname);
}
