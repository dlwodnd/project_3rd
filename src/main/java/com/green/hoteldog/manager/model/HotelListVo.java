package com.green.hoteldog.manager.model;

import com.green.hoteldog.common.entity.HotelEntity;
import lombok.Data;

@Data
public class HotelListVo {
    private Long hotelPk;
//    private BusinessDto businessDto;

    private String hotelNum;
    private String hotelNm;
    private String businessName;
    private String hotelFullAddress;
    private String hotelCall;
    private Long advertise;
    private Long approval;
    private int maxPage; // 추가: 최대 페이지 수




    public static HotelListVo hotelListVo(HotelEntity hotelEntity) {
        HotelListVo hotelDto = new HotelListVo();
        hotelDto.setHotelPk(hotelEntity.getHotelPk());
//        hotelDto.setBusinessDto(BusinessDto.toDto(hotelEntity.getBusinessEntity()));

        hotelDto.setHotelNum(hotelEntity.getHotelNum());
        hotelDto.setHotelNm(hotelEntity.getHotelNm());

        hotelDto.setBusinessName(hotelEntity.getBusinessEntity().getBusinessName());
        hotelDto.setHotelFullAddress(hotelEntity.getHotelFullAddress());
        hotelDto.setHotelCall(hotelEntity.getHotelCall());
        hotelDto.setAdvertise(hotelEntity.getAdvertise());
        hotelDto.setApproval(hotelEntity.getApproval());
        hotelDto.setMaxPage(1);

        return hotelDto;
    }
}
