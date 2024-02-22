package com.green.hoteldog.hotel.repository;

import com.green.hoteldog.common.entity.HotelSuspendedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelSuspendedRepository extends JpaRepository<HotelSuspendedEntity, Long> {
}
