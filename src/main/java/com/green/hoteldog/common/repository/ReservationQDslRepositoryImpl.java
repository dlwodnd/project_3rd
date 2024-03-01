package com.green.hoteldog.common.repository;

import com.green.hoteldog.business_user.model.QReservationInfo;
import com.green.hoteldog.business_user.model.QReservationTodayInfo;
import com.green.hoteldog.business_user.model.ReservationInfo;
import com.green.hoteldog.business_user.model.ReservationTodayInfo;
import com.green.hoteldog.common.entity.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.green.hoteldog.common.entity.QDogSizeEntity.dogSizeEntity;
import static com.green.hoteldog.common.entity.QHotelRoomInfoEntity.hotelRoomInfoEntity;
import static com.green.hoteldog.common.entity.QResComprehensiveInfoEntity.resComprehensiveInfoEntity;
import static com.green.hoteldog.common.entity.QResDogInfoEntity.resDogInfoEntity;
import static com.green.hoteldog.common.entity.QResPaymentEntity.resPaymentEntity;
import static com.green.hoteldog.common.entity.QReservationEntity.reservationEntity;
import static com.green.hoteldog.common.entity.QUserEntity.userEntity;

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

    @Override
    public Page<ReservationTodayInfo> getReservationTodayInfoList(Pageable pageable, List<ReservationEntity> reservationEntityList) {
        List<ReservationTodayInfo> reservationTodayInfoList = jpaQueryFactory
                .select(new QReservationTodayInfo(
                        reservationEntity.resNum
                        , reservationEntity.resPk
                        , userEntity.nickname
                        , hotelRoomInfoEntity.hotelRoomPk
                        , hotelRoomInfoEntity.hotelRoomNm
                        , resDogInfoEntity.resDogPk
                        , resDogInfoEntity.dogNm
                        , resDogInfoEntity.information
                        , resDogInfoEntity.age
                        , dogSizeEntity.sizePk
                        , dogSizeEntity.dogSize
                        , reservationEntity.fromDate.stringValue()
                        , reservationEntity.toDate.stringValue()
                        , userEntity.phoneNum
                        , resPaymentEntity.paymentAmount
                        , reservationEntity.resStatus))
                .from(resComprehensiveInfoEntity)
                .join(reservationEntity)
                .on(reservationEntity.in(reservationEntityList))
                .join(userEntity)
                .on(reservationEntity.userEntity.userPk.eq(userEntity.userPk))
                .join(hotelRoomInfoEntity)
                .on(resComprehensiveInfoEntity.hotelRoomInfoEntity.hotelRoomPk.eq(hotelRoomInfoEntity.hotelRoomPk))
                .join(resDogInfoEntity)
                .on(resComprehensiveInfoEntity.resDogInfoEntity.resDogPk.eq(resDogInfoEntity.resDogPk))
                .join(dogSizeEntity)
                .on(resDogInfoEntity.dogSizeEntity.sizePk.eq(dogSizeEntity.sizePk))
                .join(resPaymentEntity)
                .on(reservationEntity.resPk.eq(resPaymentEntity.reservationEntity.resPk))
                .orderBy(hotelRoomInfoEntity.dogSizeEntity.sizePk.asc(),reservationEntity.fromDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(reservationEntity.resPk.count())
                .from(resComprehensiveInfoEntity)
                .join(reservationEntity)
                .on(reservationEntity.in(reservationEntityList))
                .join(userEntity)
                .on(reservationEntity.userEntity.userPk.eq(userEntity.userPk))
                .join(hotelRoomInfoEntity)
                .on(resComprehensiveInfoEntity.hotelRoomInfoEntity.hotelRoomPk.eq(hotelRoomInfoEntity.hotelRoomPk))
                .join(resDogInfoEntity)
                .on(resComprehensiveInfoEntity.resDogInfoEntity.resDogPk.eq(resDogInfoEntity.resDogPk))
                .join(dogSizeEntity)
                .on(resDogInfoEntity.dogSizeEntity.sizePk.eq(dogSizeEntity.sizePk))
                .join(resPaymentEntity)
                .on(reservationEntity.resPk.eq(resPaymentEntity.reservationEntity.resPk));
        return PageableExecutionUtils.getPage(reservationTodayInfoList, pageable,countQuery::fetchOne);
    }

    @Override
    public Page<ReservationTodayInfo> getReservationTodayInfoList2(Pageable pageable, List<ReservationEntity> reservationEntityList) {
        List<ReservationTodayInfo> reservationTodayInfoList = jpaQueryFactory
                .select(new QReservationTodayInfo(
                        resComprehensiveInfoEntity.reservationEntity.resNum
                        , resComprehensiveInfoEntity.reservationEntity.resPk
                        , resComprehensiveInfoEntity.reservationEntity.userEntity.nickname
                        , resComprehensiveInfoEntity.hotelRoomInfoEntity.hotelRoomPk
                        , resComprehensiveInfoEntity.hotelRoomInfoEntity.hotelRoomNm
                        , resComprehensiveInfoEntity.resDogInfoEntity.resDogPk
                        , resComprehensiveInfoEntity.resDogInfoEntity.dogNm
                        , resComprehensiveInfoEntity.resDogInfoEntity.information
                        , resComprehensiveInfoEntity.resDogInfoEntity.age
                        , resComprehensiveInfoEntity.resDogInfoEntity.dogSizeEntity.sizePk
                        , resComprehensiveInfoEntity.resDogInfoEntity.dogSizeEntity.dogSize
                        , resComprehensiveInfoEntity.reservationEntity.fromDate.stringValue()
                        , resComprehensiveInfoEntity.reservationEntity.toDate.stringValue()
                        , resComprehensiveInfoEntity.reservationEntity.userEntity.phoneNum
                        , resComprehensiveInfoEntity.reservationEntity.resPaymentEntity.paymentAmount
                        , resComprehensiveInfoEntity.reservationEntity.resStatus))
                .from(resComprehensiveInfoEntity)
                .where(resComprehensiveInfoEntity.reservationEntity.in(reservationEntityList))
                .orderBy(hotelRoomInfoEntity.dogSizeEntity.sizePk.asc(),reservationEntity.fromDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(resComprehensiveInfoEntity.reservationEntity.resPk.count())
                .from(resComprehensiveInfoEntity)
                .where(resComprehensiveInfoEntity.reservationEntity.in(reservationEntityList));

        return PageableExecutionUtils.getPage(reservationTodayInfoList, pageable,countQuery::fetchOne);
    }

    @Override
    public Page<ReservationInfo> getReservationInfoList(Pageable pageable, List<ReservationEntity> reservationEntityList) {

        List<ReservationInfo> reservationInfoList = jpaQueryFactory
                .select(new QReservationInfo(
                        reservationEntity.resPk
                        , reservationEntity.resNum
                        , reservationEntity.userEntity.nickname
                        , reservationEntity.hotelEntity.hotelNm
                        , reservationEntity.fromDate.stringValue()
                        , reservationEntity.toDate.stringValue()
                        , reservationEntity.userEntity.phoneNum
                        , reservationEntity.resPaymentEntity.paymentAmount
                        , reservationEntity.resStatus))
                .from(reservationEntity)
                .where(reservationEntity.in(reservationEntityList))
                .orderBy(reservationEntity.resPk.asc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(reservationEntity.resPk.count())
                .from(reservationEntity)
                .where(reservationEntity.in(reservationEntityList));

        return PageableExecutionUtils.getPage(reservationInfoList, pageable,countQuery::fetchOne);
    }
}
