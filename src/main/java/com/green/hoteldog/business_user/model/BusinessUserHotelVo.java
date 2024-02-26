package com.green.hoteldog.business_user.model;

import com.green.hoteldog.common.entity.HotelOptionEntity;
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
    private String hotelAdvertiseToDate;
    private String hotelAdvertiseEndDate;
    private String createdAt;
    private List<String> hotelPics;
    private String hotelFullAddress;
    private HotelAddressInfo hotelAddressInfo;
    private long approval;
    private List<HotelOptionInfoVo> optionList;

    private List<HotelRoomInfo> hotelRoomInfoList;

}
