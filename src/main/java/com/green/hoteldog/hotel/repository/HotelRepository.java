package com.green.hoteldog.hotel.repository;

import com.green.hoteldog.common.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
    HotelEntity findByHotelPk(long hotelPk);
}
