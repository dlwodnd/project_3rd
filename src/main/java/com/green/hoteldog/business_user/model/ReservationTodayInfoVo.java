package com.green.hoteldog.business_user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationTodayInfoVo {
    private String resNum;
    private long resPk;
    private String nickNm;
    private long hotelRoomPk;
    private String hotelRoomNm;
    private long resDogPk;
    private String fromDate;
    private String toDate;
    private String userPhoneNum;
    private long payment; // 결제금액
    private long resStatus; // 예약 상태 0.진행중, 1.예약완료, 2.이용완료, 3.본인취소, 4사업자취소
}

