package com.green.hoteldog.business_user.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservaionListSelVo {
    private int resNum;
    private String nickNm;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String hotelCall;
    private int payment; // 결제금액
    private int resStatus; // 예약 상태 0.진행중, 1.예약완료, 2.이용완료, 3.본인취소, 4사업자취소
}
