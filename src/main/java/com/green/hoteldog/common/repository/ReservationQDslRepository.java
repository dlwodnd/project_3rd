package com.green.hoteldog.common.repository;

import com.green.hoteldog.common.entity.ResPaymentEntity;
import com.green.hoteldog.common.entity.ReservationEntity;

import java.util.List;

public interface ReservationQDslRepository {
    List<ResPaymentEntity> getResPaymentList(List<ReservationEntity> resPaymentEntityList);
}
