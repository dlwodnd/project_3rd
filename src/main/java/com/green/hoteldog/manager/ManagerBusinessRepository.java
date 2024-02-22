package com.green.hoteldog.manager;

import com.green.hoteldog.common.entity.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerBusinessRepository extends JpaRepository<BusinessEntity, Long> {

    List<BusinessEntity> findByAccountStatus(int accountStatus);

}