package com.green.hoteldog.manager;

import com.green.hoteldog.common.entity.BusinessEntity;
import com.green.hoteldog.common.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/business")
public class ManagerController {
    private final ManagerService service;


    @GetMapping("/userList")
    public List<UserEntity> getUserList() {

        return service.allUsers();
    }
}



//    @GetMapping("/userList")
//    public List<UserEntity> getUserList(@RequestParam(required=false) Integer accountStatus) {
//        if (accountStatus != null && accountStatus == 1) {
//            return service.businessUsers();
//
//        }
//        return service.allUsers();
//    }


//    @GetMapping("/userList")
////    public List<UserEntity> getUserList(@RequestParam(required=false) Integer accountStatus) {
//        if(1 == accountStatus){
//            return service.businessUsers();
//        } else if(0 == accountStatus){
//            return service.normalUsers();
//        }
//        return service.allUsers();
//    }


