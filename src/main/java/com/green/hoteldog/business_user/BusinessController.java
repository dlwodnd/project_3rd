package com.green.hoteldog.business_user;

import com.green.hoteldog.business_user.model.*;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.exceptions.BoardErrorCode;
import com.green.hoteldog.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/business")
public class BusinessController {
    private final BusinessService service;
    //영웅

    // 호텔 상태 전환
    @PostMapping("/state")
    public ResVo insHotelStateChange(@RequestBody HotelSateChangeInsDto dto){
        if (dto.getStateChange() == 0){return new ResVo(0);}
        return service.insHotelStateChange(dto);
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

    // ---------------------------------------------------------------------------------------------------

    //재웅

    //사업자 유저 호텔 등록
    @PostMapping("/registration")
    public ResVo postHotel(@RequestPart HotelInsDto dto,
                           @RequestPart MultipartFile businessCertificationFile,
                           @RequestPart List<MultipartFile> hotelPics){
        if(hotelPics.size() > 5){
            throw new CustomException(BoardErrorCode.PICS_SIZE_OVER);
        }
        dto.setBusinessCertificationFile(businessCertificationFile);
        dto.setHotelPics(hotelPics);
        return service.insHotel(dto);
    }

    //사업자 유저가 등록한 호텔 정보
    @GetMapping
    public BusinessUserHotelVo getBusinessUserHotel(){
        return service.getBusinessUserHotel();
    }

    //사업자 유저가 등록한 호텔 방 수정
    @PutMapping("/hotelRoom")
    public ResVo putHotelRoom(){
        return null;
    }


    //재웅

    // ---------------------------------------------------------------------------------------------------
    //승준
    //승준
}
