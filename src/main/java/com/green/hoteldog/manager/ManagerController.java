package com.green.hoteldog.manager;

import com.green.hoteldog.manager.model.ApprovalAdListVo;
import com.green.hoteldog.manager.model.HotelListVo;
import com.green.hoteldog.manager.model.UserListVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manager")
public class ManagerController {
    private final ManagerService service;


//    @GetMapping("/userList")
//    public List<UserEntity> getUserList() {
//
//        return service.allUsers();
//    }
//}


    @GetMapping("/userList")
    @Operation(summary = "유저목록", description = "사업자 목록 유저목록 따로")
    public List<UserListVo.BusinessVo> getUserList(@RequestParam(required = false) Integer accountStatus) {
        if (accountStatus != null && accountStatus == 1) {
            // accountStatus가 1인 경우 사업자 유저 목록 반환
            List<Long> businessUserPks = service.getBusinessUserPks();
            List<UserListVo.BusinessVo> businessUsers = service.getUsersPks(businessUserPks);
            return businessUsers;
        } else if (accountStatus != null && accountStatus != 1) {
            List<Long> normalUserPks = service.getNormalUserPks();
            List<UserListVo.BusinessVo> normalUsers = service.getUsersPks(normalUserPks);
            // 그 외의 경우에는 모든 유저 목록 반환
            return normalUsers;
        }
        return null;
    }

   // 대기 유저 사업자유저 전환
   @Operation(summary = "대기 유저 사업자유저 전환", description = "대기 유저 사업자유저 전환")

   @PutMapping("/BusinessTransform")
   public void BusinessTransform(@RequestParam long businessPk) {
       service.updateAccountStatusTo1(businessPk);
   }


    // 호텔 목록을 가져오는 메서드
    @GetMapping("/hotelList")
    @Operation(summary = "호텔목록", description = "호텔목록")
    public List<HotelListVo> getManagementHotelList() {
        return service.getManagementHotelList();
    }

    // 승인 대기 호텔목록 가져오는 메서드
    @GetMapping("/hotelAccountStatus")
    @Operation(summary = "승인 대길 호텔", description = "광고 승인 목록처리")

    public List<HotelListVo> getManagementApprovalHotelList() {
        return service.getManagementHotelByBusinessEntity_AccountStatus();
    }

    @GetMapping("/adHotelList/approval")
    @Operation(summary = "광고 승인목록", description = "광고 승인 목록처리")

    public List<ApprovalAdListVo> getApprovalAdList() {
        return service.getApprovalAdList();
    }

    @PatchMapping("/approval")
    @Operation(summary = "광고 승인", description = "광고 승인 처리")

    public void approvalAd(@RequestParam long hotelPk) {
        service.updateHotelAdvertiseEntityBySignStatus( hotelPk);
    }

    @PatchMapping("/adRefuse")
    @Operation(summary = "광고 승인 거절", description = "광고 승인 거절 처리")

    public void updateHotelAdvertiseEntityBySignStatusAndCancelReason( String cancelReason, long hotelPk){
        service.updateHotelAdvertiseEntityBySignStatusAndCancelReason(cancelReason, hotelPk);
    }


    @PatchMapping("/hotelApproval")
    @Operation(summary = "호텔 승인", description = "호텔 승인 처리")
    public void updateHotelEntityByApproval(@RequestParam long hotelPk) {
        service.updateHotelEntityByApproval(hotelPk);
    }


    @PatchMapping("/suspendApproval")
    @Operation(summary = "호텔 중지 신청 승인", description = "호텔 중지 신청 승인 처리")
    public void updateHotelSuspendedEntityBySignStatus(@RequestParam long hotelPk) {
        service.updateHotelSuspendedEntityBySignStatus(hotelPk);
    }


    @PatchMapping("/suspendRefuse")
    @Operation(summary = "호텔 중지 신청 거절", description = "호텔 중지 신청 거절 사유까지 처리")
    public void updateHotelAdvertiseEntityBySignStatusAndCancelReason(@RequestParam("suspendedReason") String suspendedReason, @RequestParam("hotelPk") Long hotelPk) {
        service.updateHotelAdvertiseEntityBySignStatusAndCancel( suspendedReason, hotelPk);
    }








    @PatchMapping("/suspendRefusee")
    @Operation(summary = "호텔 중지 신청 거절1", description = "호텔 중지 신청 거절 사유까지 처리")
    public void updateHotelSuspendedEntityBySignStatusAndSuspendedReason( String suspendedReason,@RequestParam long hotelPk){
        service.updateHotelSuspendedEntityBySignStatusAndSuspendedReason( suspendedReason, hotelPk);
    }
}




