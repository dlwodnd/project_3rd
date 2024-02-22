package com.green.hoteldog.common.repository;

import com.green.hoteldog.common.entity.BusinessEntity;
import com.green.hoteldog.common.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelMangerRepository extends JpaRepository<BusinessEntity, Long> {

    List<HotelEntity> findByAccountStatus(int accountStatus);

}
