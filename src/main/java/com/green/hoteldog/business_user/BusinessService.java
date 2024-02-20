package com.green.hoteldog.business_user;

import com.green.hoteldog.business_user.model.BusinessUserHotelRoomVo;
import com.green.hoteldog.business_user.model.BusinessUserHotelVo;
import com.green.hoteldog.business_user.model.HotelAdvertiseApplicationDto;
import com.green.hoteldog.business_user.model.ReservaionListSelVo;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessService {
    private final AuthenticationFacade authenticationFacade;

    // 호텔 삭제
    public ResVo delHotel(){
        return new ResVo(0);
    }

    // 광고 신청
    public ResVo postHotelAdvertiseApplication(HotelAdvertiseApplicationDto dto){
        // 1. 유저가 사업자유저인지 체크
        dto.builder().iuser(authenticationFacade.getLoginUserPk()).build();

        // 2. 광고테이블 인서트, 결제 테이블 인서트


        // 3. 결제 완료 후 광고테이블 결제여부 업데이트


        return new ResVo(0);
    }

    // 호텔 방 등록
    public ResVo postHotelRoom(){
        return new ResVo(0);
    }

    // 예약 리스트 출력
    public List<ReservaionListSelVo> getHotelReservationList(){
        return null;
    }
    // 영웅

    //재웅

    //사업자 유저 호텔 등록
    public ResVo insHotelInfo(){
        return null;
    }
    //사업자 유저가 등록한 호텔 리스트
    public List<BusinessUserHotelVo> getBusinessUserHotel(){
        return null;
    }
    //사업자 유저가 등록한 호텔 방 수정
    public ResVo putHotelRoomInfo(){
        return null;
    }
    //사업자 유저가 등록한 방 리스트
    public List<BusinessUserHotelRoomVo> getBusinessUserHotelRoomList(){
        return null;
    }
    //재웅
}
