package com.green.hoteldog.manager;

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

//    @GetMapping("/uesrList")
//    public List<UserEntity> getAllUser(){
//        return service.allUsers();
//    }
//    @GetMapping("/uesrList")
//    public List<UserEntity> getUser() {
//        return service.normalUsers();
//    }
//
//    @GetMapping("/uesrList")
//    public List<UserEntity> businessUsers(){
//        return service.businessUsers();
//    }
//

//인티지 오더스 유저타입
//    @GetMapping("/userList")
//    public List<UserEntity> getUserList(@RequestParam(required=false) String userType) {
//        if("business".equals(userType)){
//            return service.businessUsers();
//        } else if("normal".equals(userType)){
//            return service.normalUsers();
//        }
//        return service.allUsers();
//    }

    @GetMapping("/userList")
    public List<UserEntity> getUserList(@RequestParam(required=false) Integer accountStatus) {
        if(1 == accountStatus){
            return service.businessUsers();
        } else if(0 == accountStatus){
            return service.normalUsers();
        }
        return service.allUsers();
    }


}

