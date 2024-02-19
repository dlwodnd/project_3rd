package com.green.hoteldog.business_user;

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
        // 2. 광고테이블 인서트, 결제 테이블 인서트
        // 3. 결제 완료 후 광고테이블 결제여부 업데이트
        dto.builder().iuser(authenticationFacade.getLoginUserPk()).build();

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
}
