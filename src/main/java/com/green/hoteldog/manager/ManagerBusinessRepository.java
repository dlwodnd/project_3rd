package com.green.hoteldog.manager;

import com.green.hoteldog.entity.BusinessEntity;
import com.green.hoteldog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerBusinessRepository extends JpaRepository<BusinessEntity, Long> {

    List<BusinessEntity> findByAccountStatus(int accountStatus);

}