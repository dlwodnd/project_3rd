package com.green.hoteldog.business_user.model;

import lombok.Data;

@Data
public class HotelSateChangeInsDto {
    private int stateChange; // 1 - 중지신청, 2 - 철회
    private int hotelPk;
    private String suspendReason;
}
