package com.green.hoteldog.manager;

import com.green.hoteldog.manager.model.ApprovalAdListVo;
import com.green.hoteldog.manager.model.HotelListVo;
import com.green.hoteldog.manager.model.UserListVo;
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
   @PutMapping("/BusinessTransform")
   public void BusinessTransform(@RequestParam long businessPk) {
       service.updateAccountStatusTo1(businessPk);
   }


    // 호텔 목록을 가져오는 메서드
    @GetMapping("/hotelList")
    public List<HotelListVo> getManagementHotelList() {
        return service.getManagementHotelList();
    }

    // 승인 대기 호텔목록 가져오는 메서드
    @GetMapping("/hotelAccountStatus")
    public List<HotelListVo> getManagementApprovalHotelList() {
        return service.getManagementHotelByBusinessEntity_AccountStatus();
    }

    @GetMapping("/adHotelList/approval")
    public List<ApprovalAdListVo> getApprovalAdList() {
        return service.getApprovalAdList();
    }
}




