package com.green.hoteldog.business_user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BusinessHotelRoomPutDto {
    private long hotelRoomPk;

    private String hotelRoomNm;

    private long sizePk;

    @JsonIgnore
    private MultipartFile roomPic;

    private long hotelRoomEa;

    private long hotelRoomCost;

    private long maximum;

    private String discountPer;

}
