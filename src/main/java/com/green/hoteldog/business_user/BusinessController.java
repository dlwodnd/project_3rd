package com.green.hoteldog.business_user;

import com.green.hoteldog.business_user.model.*;
import com.green.hoteldog.common.ResVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/business")
public class BusinessController {
    private final BusinessService service;

    // 호텔 삭제
    @DeleteMapping
    public  ResVo delHotel(){
        return service.delHotel();
    }

    // 광고 신청
    @GetMapping("/advertise")
    public ResVo postHotelAdvertiseApplication(HotelAdvertiseApplicationDto dto){
        return service.postHotelAdvertiseApplication(dto);
    }

    // 호텔 방 등록
    @PostMapping("/hotelRoom")
    public ResVo postHotelRoom(List<HotelRoomInsDto> dto){
        return service.postHotelRoom(dto);
    }

    // 예약 리스트 출력
    @GetMapping("/reservation")
    public List<ReservaionListSelVo> getHotelReservationList(int hotelPk){
        return service.getHotelReservationList(hotelPk);
    }
    // 영웅

    //재웅

    //사업자 유저 호텔 등록
    @PostMapping
    public ResVo postHotel(){
        return null;
    }
    //사업자 유저가 등록한 호텔 리스트
    @GetMapping
    public List<BusinessUserHotelVo> getBusinessUserHotel(){
        return null;
    }
    //사업자 유저가 등록한 호텔 방 수정
    @PutMapping("/hotelRoom")
    public ResVo putHotelRoom(){
        return null;
    }
    //사업자 유저가 등록한 방 리스트
    @GetMapping("/hotelRoomList")
    public List<BusinessUserHotelRoomVo> getBusinessUserHotelRoom(){
        return null;
    }

    //재웅
}
