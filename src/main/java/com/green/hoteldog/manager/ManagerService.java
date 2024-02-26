package com.green.hoteldog.manager;

import com.green.hoteldog.common.entity.BusinessEntity;
import com.green.hoteldog.common.entity.HotelAdvertiseEntity;
import com.green.hoteldog.common.entity.HotelEntity;

import com.green.hoteldog.common.entity.PaymentAdEntity;
import com.green.hoteldog.common.repository.*;
import com.green.hoteldog.manager.model.ApprovalAdListVo;
import com.green.hoteldog.manager.model.HotelListVo;
import com.green.hoteldog.manager.model.HotelVo;
import com.green.hoteldog.manager.model.UserListVo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagerService {
    private final UserRepository managerRepository;
    private final BusinessRepository businessEntityRepository;
    private final HotelRepository hotelRepository;
    private final HotelAdvertiseRepository hotelAdvertiseRepository;
    private final PaymentAdRepository paymentAdRepository;

    // 모든유저
//    public List <UserEntity> allUsers(List<UserEntity> nomalUsers){
//        return managerRepository.findAllByOrderByCreatedAtDesc();
//    }


    // t_buisiness 테이블에서 account_status가 1인 사용자의 user_pk 목록을 가져오는 메서드
    public List<Long> getBusinessUserPks() {
        return businessEntityRepository.findByAccountStatus(1)
                .stream()
                .map(businessEntity -> businessEntity.getUserEntity().getUserPk())
                .collect(Collectors.toList());
    }

    // t_buisiness 테이블에서 account_status가 1이 아닌 사용자의 user_pk 목록을 가져오는 메서드
    public List<Long> getNormalUserPks() {
        return businessEntityRepository.findByAccountStatusNot(1)
                .stream()
                .map(businessEntity -> businessEntity.getUserEntity().getUserPk())
                .collect(Collectors.toList());
    }

    // 주어진 user_pk 목록에 해당하는 사용자 목록을 가져오는 메서드


    public List<UserListVo.BusinessVo> getUsersPks(List<Long> userPks) {
        List<BusinessEntity> users = businessEntityRepository.findByUserEntity_UserPkIn(userPks);
        return users.stream()
                .map(BusinessEntity -> UserListVo.BusinessVo.UserList(BusinessEntity))
                .collect(Collectors.toList());
    }


    // 유저를 승인대기에서 비지니스로ㅗ 바꿈
    @Transactional
    public void updateAccountStatusTo1(long businessPk) {
        businessEntityRepository.updateBusinessEntityByAccountStatus(1, businessPk);
    }


    // 호텔 목록을 가져오는 메서드
    public List<HotelListVo> getManagementHotelList() {
        List<HotelEntity> hotelEntities = hotelRepository.findAll();
        return hotelEntities.stream()
                .map(hotelEntity -> HotelListVo.hotelListVo(hotelEntity))
                .collect(Collectors.toList());
    }

    // 승인 대기 호텔목록 가져오는 메서드
    public List<HotelListVo> getManagementHotelByBusinessEntity_AccountStatus() {
        List<HotelEntity> hotelEntities = hotelRepository.findByBusinessEntity_AccountStatus(2);
        return hotelEntities.stream()
                .map(hotelEntity -> HotelListVo.hotelListVo(hotelEntity))
                .collect(Collectors.toList());
    }

    public List<ApprovalAdListVo> getApprovalAdList() {
        List<PaymentAdEntity> paymentAdEntities = paymentAdRepository.findAll();
        return paymentAdEntities.stream()
                .map(paymentAdEntity -> ApprovalAdListVo.approvalAdListVo(paymentAdEntity))
                .collect(Collectors.toList());
    }
}