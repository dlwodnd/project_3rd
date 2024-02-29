package com.green.hoteldog.common.repository;

import com.green.hoteldog.user.models.HotelRoomDateProcDto;

import java.util.List;

public interface HotelResRoomQDslRepository {
    long updateAllHotelResRoomRefundCount(List<HotelRoomDateProcDto> hotelRoomDateProcDtoList);
    long updateHotelResRoomRefundCount(HotelRoomDateProcDto hotelRoomDateProcDto);
}
