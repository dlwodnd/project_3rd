package com.green.hoteldog.manager;

import com.green.hoteldog.common.entity.BusinessEntity;
import com.green.hoteldog.common.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
// 모든유저
    public List <UserEntity> allUsers(){
        return managerRepository.findAllByOrderByCreatedAtDesc();
    }
/// 사업자유저
    public List<UserEntity> businessUsers(){
        List<UserEntity> businessUsers = managerRepository.findUsersByBusinessAccountStatus(1l);
        return businessUsers;
    }
// 일반유저

}
