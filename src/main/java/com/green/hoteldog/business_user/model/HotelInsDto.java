package com.green.hoteldog.business_user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelInsDto {
    private String hotelNm;
    private String hotelDetailInfo;
    private String businessNum;
    private String hotelCall;
    @JsonIgnore
    private MultipartFile businessCertificationFile;
    @JsonIgnore
    private List<MultipartFile> hotelPics;
}