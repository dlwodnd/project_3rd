package com.green.hoteldog.business_user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUserHotelVo {
    private long hotelPk;
    private String hotelNum;
    private String hotelNm;
    private String hotelDetailInfo;
    private String businessNum;
    private String hotelCall;
    private long advertise;
    private String createdAt;
    private List<String> optionNmList;
    private List<String> hotelPics;
    private String hotelFullAddress;
    private HotelAddressInfo hotelAddressInfo;
    private long approval;

    List<HotelRoomInfo> hotelRoomInfoList;

}
