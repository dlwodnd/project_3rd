package com.green.hoteldog.hotel.repository;

import com.green.hoteldog.common.entity.HotelRoomInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRoomRepository extends JpaRepository<HotelRoomInfoEntity, Long> {
}
