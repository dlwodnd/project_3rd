package com.green.hoteldog.business_user.model;

import lombok.Data;

@Data
public class HotelSateChangeInsDto {
    private Long stateChange; // 1 - 중지신청, 2 - 철회
    private Long hotelPk;
    private String suspendReason;
}
