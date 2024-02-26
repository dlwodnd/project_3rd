package com.green.hoteldog.manager.model;

import com.green.hoteldog.common.entity.HotelEntity;
import lombok.Data;

@Data
public class HotelListVo {
    private Long hotelPk;
//    private BusinessDto businessDto;

    private String hotelNum;
    private String hotelNm;
    private String userNickname;
    private String userAddress;
    private String phoneNum;



    public static HotelListVo hotelListVo(HotelEntity hotelEntity) {
        HotelListVo hotelDto = new HotelListVo();
        hotelDto.setHotelPk(hotelEntity.getHotelPk());
//        hotelDto.setBusinessDto(BusinessDto.toDto(hotelEntity.getBusinessEntity()));

        hotelDto.setHotelNum(hotelEntity.getHotelNum());
        hotelDto.setHotelNm(hotelEntity.getHotelNm());
        hotelDto.setUserNickname(hotelEntity.getBusinessEntity().getUserEntity().getNickname());
        hotelDto.setUserAddress(hotelEntity.getBusinessEntity().getUserEntity().getUserAddress());
        hotelDto.setPhoneNum(hotelEntity.getBusinessEntity().getUserEntity().getPhoneNum());


        return hotelDto;
    }
}
