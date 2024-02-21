package com.green.hoteldog.manager;

import com.green.hoteldog.entity.BusinessEntity;
import com.green.hoteldog.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<UserEntity> getUserList(@RequestParam(required = false) Integer accountStatus) {
        if (accountStatus != null && accountStatus == 1) {
            // accountStatus가 1인 경우 사업자 유저 목록 반환
            List<Long> businessUserPks = service.getBusinessUserPks();
            List<UserEntity> businessUsers = service.getUsersPks(businessUserPks);
            return businessUsers;
        } else if (accountStatus != null && accountStatus != 1) {
            List<Long> nomalsUserPks = service.getBusinessUserPks();
            List<UserEntity> nomalUsers = service.getNormalUsersPks(nomalsUserPks);
            // 그 외의 경우에는 모든 유저 목록 반환
            return nomalUsers;
        } return null;
    }
}




