package com.green.hoteldog.manager;

import com.green.hoteldog.common.entity.UserEntity;
import com.green.hoteldog.common.repository.ManagerBusinessRepository;
import com.green.hoteldog.common.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final ManagerBusinessRepository managerBusinessRepository;

    // 모든유저
    public List <UserEntity> allUsers(List<UserEntity> nomalUsers){
        return managerRepository.findAllByOrderByCreatedAtDesc();
    }




    // t_buisiness 테이블에서 account_status가 1인 사용자의 user_pk 목록을 가져오는 메서드
    public List<Long> getBusinessUserPks() {
        return managerBusinessRepository.findByAccountStatus(1)
                .stream()
                .map(businessEntity -> businessEntity.getUserEntity().getUserPk())
                .collect(Collectors.toList());
    }



    // 주어진 user_pk 목록에 해당하는 사용자 목록을 가져오는 메서드
    public List<UserEntity> getUsersPks(List<Long> userPks) {
        return managerRepository.findAllByUserPkInOrderByCreatedAtDesc(userPks);
    }
    public  List<UserEntity> getNormalUsersPks(List<Long> nomalsUserPks){
        return managerRepository.findAllByUserPkNotInOrderByCreatedAtDesc(getBusinessUserPks());
    }
}