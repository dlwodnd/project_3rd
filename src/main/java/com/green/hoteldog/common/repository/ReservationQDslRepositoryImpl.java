package com.green.hoteldog.common.repository;

import com.green.hoteldog.common.entity.ResPaymentEntity;
import com.green.hoteldog.common.entity.ReservationEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.green.hoteldog.common.entity.QResPaymentEntity.resPaymentEntity;
import static com.green.hoteldog.common.entity.QReservationEntity.reservationEntity;

@RequiredArgsConstructor
public class ReservationQDslRepositoryImpl implements ReservationQDslRepository{
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<ResPaymentEntity> getResPaymentList(List<ReservationEntity> reservationEntityList) {

        return jpaQueryFactory.selectFrom(resPaymentEntity)
                .join(reservationEntity)
                .on(reservationEntity.resPk.eq(resPaymentEntity.reservationEntity.resPk))
                .where(reservationEntity.in(reservationEntityList))
                .orderBy(reservationEntity.resPk.desc()).fetch();
    }
}
