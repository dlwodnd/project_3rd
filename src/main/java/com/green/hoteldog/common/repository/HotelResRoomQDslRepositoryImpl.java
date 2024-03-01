package com.green.hoteldog.common.repository;

import com.green.hoteldog.hotel.model.HotelRoomInfoVo;
import com.green.hoteldog.hotel.model.QHotelRoomInfoVo;
import com.green.hoteldog.user.models.HotelRoomDateProcDto;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.green.hoteldog.common.entity.QHotelResRoomEntity.hotelResRoomEntity;


import java.util.List;

@RequiredArgsConstructor
public class HotelResRoomQDslRepositoryImpl implements HotelResRoomQDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void updateAllHotelResRoomRefundCount(List<HotelRoomDateProcDto> hotelRoomDateProcDtoList) {
        for (HotelRoomDateProcDto hotelRoomDateProcDto : hotelRoomDateProcDtoList) {
            jpaQueryFactory
                    .update(hotelResRoomEntity)
                    .set(hotelResRoomEntity.roomLeftEa, hotelResRoomEntity.roomLeftEa.add(1))
                    .where(hotelResRoomEntity.hotelRoomByDatePk.eq(hotelRoomDateProcDto.getHotelRoomInfoEntity().getHotelRoomPk())
                            , hotelResRoomEntity.roomDate.between(hotelRoomDateProcDto.getFromDate(), hotelRoomDateProcDto.getToDate().plusDays(1)))
                    .execute();
        }

    }

    @Override
    public void updateHotelResRoomRefundCount(HotelRoomDateProcDto hotelRoomDateProcDto) {
        jpaQueryFactory
                .update(hotelResRoomEntity)
                .set(hotelResRoomEntity.roomLeftEa, hotelResRoomEntity.roomLeftEa.add(1))
                .where(hotelResRoomEntity.hotelRoomByDatePk.eq(hotelRoomDateProcDto.getHotelRoomInfoEntity().getHotelRoomPk())
                        , hotelResRoomEntity.roomDate.between(hotelRoomDateProcDto.getFromDate(), hotelRoomDateProcDto.getToDate().plusDays(1)))
                .execute();

    }

    @Override
    public List<HotelRoomInfoVo> findResAbleHotelRoomInfo(long dogCount) {
        List<HotelRoomInfoVo> hotelRoomInfoVoList = jpaQueryFactory
                .select(new QHotelRoomInfoVo(
                        hotelResRoomEntity.hotelRoomInfoEntity.hotelRoomPk
                        , hotelResRoomEntity.hotelRoomInfoEntity.hotelRoomNm
                        , hotelResRoomEntity.roomLeftEa.subtract(dogCount).as("hotelRoomEa")
                        , hotelResRoomEntity.hotelRoomInfoEntity.hotelRoomCost
                        , hotelResRoomEntity.hotelRoomInfoEntity.roomPic
                        , hotelResRoomEntity.hotelRoomInfoEntity.maximum
                        , new CaseBuilder().when(hotelResRoomEntity.roomLeftEa.subtract(dogCount).goe(0))
                        .then(0L).otherwise(1L).as("roomAble")
                ))
                .from(hotelResRoomEntity)
                .groupBy(hotelResRoomEntity.hotelRoomInfoEntity.hotelRoomPk)
                .orderBy(hotelResRoomEntity.roomLeftEa.asc())
                .fetch();
        return hotelRoomInfoVoList;
    }


}
