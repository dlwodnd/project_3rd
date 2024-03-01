package com.green.hoteldog.business_user;

import com.green.hoteldog.business_user.model.*;
import com.green.hoteldog.common.Const;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.exceptions.BoardErrorCode;
import com.green.hoteldog.exceptions.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/business")
public class BusinessController {
    private final BusinessService service;


    // 호텔 상태 전환
    @PostMapping("/state")
    public ResVo insHotelStateChange(@RequestBody HotelSateChangeInsDto dto){
        return service.insHotelStateChange(dto);
    }

    // 광고 신청
    @GetMapping("/advertise")
    public ResVo postHotelAdvertiseApplication(@RequestBody HotelAdvertiseApplicationDto dto){
        return service.postHotelAdvertiseApplication(dto);
    }

    // 예약 리스트 출력
    @GetMapping("/reservation")
    public ReservationInfoVo getHotelReservationList(@RequestParam @PageableDefault(page = 1,size = 10) Pageable pageable){
        return service.getHotelReservationList(pageable);
    }
    // 오늘 예약 정보 리스트
    @GetMapping("/reservation/today")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호", required = true)
    })
    public ReservationTodayInfoVo getHotelReservationListToday(@RequestParam int page){
        if(page < 1){
            page = 1;
        }
        Pageable pageable = PageRequest.of(page, Const.COMMON_PAGE_SIZE);
        return service.getHotelReservationTodayList(pageable);
    }


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
    //사업자 유저 호텔 정보 수정
    @PutMapping("/hotel")
    @Operation(summary = "사업자 유저 호텔 정보 수정", description = "사업자 유저가 등록한 호텔 정보를 수정합니다.")
    public ResVo putHotel(@RequestPart HotelUpdateDto dto,
                          @RequestPart(required = false) List<MultipartFile> hotelPics){
        if(hotelPics.size() > 5){
            throw new CustomException(BoardErrorCode.PICS_SIZE_OVER);
        }
        dto.setHotelPics(hotelPics);
        return service.putHotel(dto);
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
    @Parameters({
            @Parameter(name = "resPk", description = "예약 pk", required = true)
    })
    public List<HotelRoomAndDogInfoVo> getHotelRoomAndDogInfo(long resPk){

        return service.getHotelRoomAndDogInfo(resPk);
    }

    //호텔 방 활성화 & 비활성화 토글
    @PatchMapping("/hotelRoom")
    @Operation(summary = "호텔 방 활성화 & 비활성화 토글", description = "호텔 방 활성화 & 비활성화 토글")
    public ResVo toggleHotelRoomActive(@RequestBody HotelRoomToggleDto dto){

        return service.toggleHotelRoomActive(dto);
    }

    //사업자 유저 회원 탈퇴
    @PostMapping("/withdrawal")
    @Operation(summary = "사업자 유저 회원 탈퇴", description = "사업자 유저 회원 탈퇴")
    public ResVo postBusinessUserWithdrawal(){
        return service.postBusinessUserWithdrawal();
    }
    //사업자 호텔에 등록된 예약 승인처리
    @PatchMapping("/reservation/approval")
    @Operation(summary = "사업자 호텔에 등록된 예약 체크인 처리", description = "사업자 호텔에 등록된 예약 체크인 처리")
    @Parameter(name = "resPkList", description = "예약 pk 리스트", required = true)
    public ResVo patchReservationApproval(List<Long> resPkList){
        return service.reservationApproval(resPkList);
    }

    //사업자 호텔에 등록된 예약 취소처리
    @PostMapping("/reservation/cancel")
    @Operation(summary = "사업자 호텔에 등록된 예약 체크인 처리", description = "사업자 호텔에 등록된 예약 체크인 처리")
    public ResVo patchReservationCheckIn(@RequestBody ResCancelDto dto){
        return service.reservationCancel(dto);
    }

    //사업자 호텔에 등록된 예약 체크인 처리
    @PatchMapping("/reservation/checkIn")
    @Parameter(name = "resPkList", description = "예약 pk 리스트", required = true)
    @Operation(summary = "사업자 호텔에 등록된 예약 체크인 처리", description = "사업자 호텔에 등록된 예약 체크인 처리")
    public ResVo patchReservationCheckIn(@RequestBody List<Long> resPkList){
        return service.reservationCheckIn(resPkList);
    }

    //사업자 호텔에 등록된 예약 체크아웃 처리
    @PatchMapping("/reservation/checkOut")
    @Parameter(name = "resPkList", description = "예약 pk 리스트", required = true)
    @Operation(summary = "사업자 호텔에 등록된 예약 체크인 처리", description = "사업자 호텔에 등록된 예약 체크인 처리")
    public ResVo patchReservationCheckOut(@RequestBody List<Long> resPkList){
        return service.reservationCheckOut(resPkList);
    }


}
