package com.green.hoteldog.business_user;

import com.green.hoteldog.business_user.model.*;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.common.entity.HotelEntity;
import com.green.hoteldog.exceptions.BoardErrorCode;
import com.green.hoteldog.exceptions.CustomException;
import com.green.hoteldog.user.models.UserSignupDto;
import io.swagger.v3.oas.annotations.Operation;
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
    public ResVo postHotelAdvertiseApplication(){
        return service.postHotelAdvertiseApplication();
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
    @Operation(summary = "사업자 유저 호텔 등록", description = "사업자 유저가 호텔을 등록합니다.")
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
    @Operation(summary = "사업자 유저 호텔 정보", description = "사업자 유저가 등록한 호텔 정보를 불러옵니다.")
    public BusinessUserHotelVo getBusinessUserHotel(){
        return service.getBusinessUserHotel();
    }

    //사업자 유저가 등록한 호텔 방 수정
    @PutMapping("/hotelRoom")
    @Operation(summary = "사업자 유저 호텔 방 수정", description = "사업자 유저가 등록한 호텔 방 정보를 수정합니다.")
    public ResVo putHotelRoom(@RequestPart(required = false) MultipartFile roomPic,
                              @RequestPart BusinessHotelRoomPutDto dto){
        dto.setRoomPic(roomPic);
        if(roomPic == null){
            dto.setRoomPic(null);
        }
        return service.putHotelRoomInfo(dto);
    }

    //사업자 유저 호텔에 예약된 강아지 방 정보 리스트
    @GetMapping("/reservation/HotelRoomAndDogInfo")
    @Operation(summary = "사업자 유저 호텔에 예약된 강아지 방 정보 리스트", description = "사업자 유저가 등록한 호텔에 예약된 강아지 방 정보 리스트를 불러옵니다.")
    public List<HotelRoomAndDogInfoVo> getHotelRoomAndDogInfo(long resPk){

        return service.getHotelRoomAndDogInfo(resPk);
    }

    //호텔 방 활성화 & 비활성화 토글
    @PatchMapping("/hotelRoom")
    @Operation(summary = "호텔 방 활성화 & 비활성화 토글", description = "호텔 방 활성화 & 비활성화 토글")
    public ResVo toggleHotelRoomActive(@RequestBody long hotelRoomPk){

        return service.toggleHotelRoomActive(hotelRoomPk);
    }

    //사업자 유저 회원 탈퇴
    @PostMapping("/withdrawal")
    @Operation(summary = "사업자 유저 회원 탈퇴", description = "사업자 유저 회원 탈퇴")
    public ResVo postBusinessUserWithdrawal(@RequestBody String upw){

        return service.postBusinessUserWithdrawal(upw);
    }

    //재웅

    // ---------------------------------------------------------------------------------------------------
    //승준
    //승준
}
