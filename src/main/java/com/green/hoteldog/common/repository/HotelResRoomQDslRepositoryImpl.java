package com.green.hoteldog.common.repository;

import com.green.hoteldog.user.models.HotelRoomDateProcDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.green.hoteldog.common.entity.QHotelResRoomEntity.hotelResRoomEntity;


import java.util.List;

@RequiredArgsConstructor
public class HotelResRoomQDslRepositoryImpl implements HotelResRoomQDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public long updateHotelResRoomRefundCount(List<HotelRoomDateProcDto> hotelRoomDateProcDtoList) {
        for (HotelRoomDateProcDto hotelRoomDateProcDto : hotelRoomDateProcDtoList) {
            jpaQueryFactory
                    .update(hotelResRoomEntity)
                    .set(hotelResRoomEntity.roomLeftEa, hotelResRoomEntity.roomLeftEa.add(1))
                    .where(hotelResRoomEntity.hotelRoomByDatePk.eq(hotelRoomDateProcDto.getHotelRoomInfoEntity().getHotelRoomPk())
                            , hotelResRoomEntity.roomDate.between(hotelRoomDateProcDto.getFromDate(), hotelRoomDateProcDto.getToDate().plusDays(1)))
                    .execute();
        }
        return 0;
    }
}
