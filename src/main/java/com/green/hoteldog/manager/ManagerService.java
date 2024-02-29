package com.green.hoteldog.manager;

import com.green.hoteldog.common.entity.*;

import com.green.hoteldog.common.entity.jpa_enum.UserRoleEnum;
import com.green.hoteldog.common.repository.*;
import com.green.hoteldog.manager.model.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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
    private final HotelAdvertiseRepository hotelAdvertiseEntityRepository;
    private final HotelSuspendedRepository hotelSuspendedRepository;

    // 모든유저
//    public List <UserEntity> allUsers(List<UserEntity> nomalUsers){
//        return managerRepository.findAllByOrderByCreatedAtDesc();
//    }


    // t_buisiness 테이블에서 account_status가 1인 사용자의 user_pk 목록을 가져오는 메서드
    public List<Long> getBusinessUserPks() {
        /*return businessEntityRepository.findByAccountStatus(1)
                .stream()
                .map(businessEntity -> businessEntity.getUserEntity().getUserPk())
                .collect(Collectors.toList());*/
        return null;
    }

    // t_buisiness 테이블에서 account_status가 1이 아닌 사용자의 user_pk 목록을 가져오는 메서드
    public List<Long> getNormalUserPks() {
        /*return businessEntityRepository.findByAccountStatusNot(1)
                .stream()
                .map(businessEntity -> businessEntity.getUserEntity().getUserPk())
                .collect(Collectors.toList());*/
        return null;
    }

    // 주어진 user_pk 목록에 해당하는 사용자 목록을 가져오는 메서드

    public List<UserListVo.BusinessVo> getUsersPks(List<Long> userPks, Pageable pageable) {

        List<BusinessEntity> users = businessEntityRepository.findByUserEntity_UserPkInOrderByCreatedAtDesc(userPks, pageable);
        return users.stream()
                .map(BusinessEntity -> UserListVo.BusinessVo.UserList(BusinessEntity))
                .collect(Collectors.toList());
    }

    public List<UserListVo2>getUsers(UserRoleEnum userRole, Pageable pageable){
        UserListVo2 vo2 = new UserListVo2();
        long totalRecords =  managerRepository.count();
        int maxPage = (int) Math.ceil((double) totalRecords / pageable.getPageSize());
        vo2.setMaxPage(maxPage);
        List<UserEntity> users = managerRepository.findByUserRoleOrderByCreatedAtDesc(UserRoleEnum.USER, pageable);
        return users.stream()
                .map(UserEntity -> UserListVo2.UserList2(UserEntity))
                .collect(Collectors.toList());
    }

    public List<UserListVo2>businessUsers(UserRoleEnum userRole, Pageable pageable){
        UserListVo2 vo2 = new UserListVo2();
        long totalRecords =  managerRepository.count();
        int maxPage = (int) Math.ceil((double) totalRecords / pageable.getPageSize());
        vo2.setMaxPage(maxPage);
        List<UserEntity> users = managerRepository.findByUserRoleOrderByCreatedAtDesc(UserRoleEnum.BUSINESS_USER, pageable);
        return users.stream()
                .map(UserEntity -> UserListVo2.UserList2(UserEntity))
                .collect(Collectors.toList());
    }



    // 유저를 승인대기에서 비지니스로 바꿈
    @Transactional
    public void updateAccountStatusTo1(long businessPk) {
        /*businessEntityRepository.updateBusinessEntityByAccountStatus(1, businessPk);*/
    }


    // 호텔 목록을 가져오는 메서드
    public List<HotelListVo> getManagementHotelList(Pageable pageable) {
        HotelListVo hotelListVo = new HotelListVo();
        long totalRecords =  hotelRepository.count();
        int maxPage = (int) Math.ceil((double) totalRecords / pageable.getPageSize());
        hotelListVo.setMaxPage(maxPage);
        List<HotelEntity> hotelEntities = hotelRepository.findAllByOrderByCreatedAtDesc(pageable);
        return hotelEntities.stream()
                .map(hotelEntity -> HotelListVo.hotelListVo(hotelEntity))
                .collect(Collectors.toList());
    }

    // 승인 대기 호텔목록 가져오는 메서드
    public List<HotelListVo> getManagementHotelByBusinessEntity_AccountStatus(Pageable pageable) {
        HotelListVo hotelListVo = new HotelListVo();
        long totalRecords =  hotelRepository.count();
        int maxPage = (int) Math.ceil((double) totalRecords / pageable.getPageSize());
        hotelListVo.setMaxPage(maxPage);
        List<HotelEntity> hotelEntities = hotelRepository.findHotelEntityByApprovalOrderByCreatedAtDesc(0, pageable);
        return hotelEntities.stream()
                .map(hotelEntity -> HotelListVo.hotelListVo(hotelEntity))
                .collect(Collectors.toList());
    }

//    // 광고 승인 목록
//    public List<ApprovalAdListVo> getApprovalAdList(Pageable pagleable) {
//        List<PaymentAdEntity> paymentAdEntities = paymentAdRepository.findByHotelAdvertiseEntitySignStatus(1, pagleable);
//        return paymentAdEntities.stream()
//                .map(paymentAdEntity -> ApprovalAdListVo.approvalAdListVo(paymentAdEntity))
//                .collect(Collectors.toList());
//    }

//    // 호텔 광고 승인
//    @Transactional
//    public void updateHotelAdvertiseEntityBySignStatus(long hotelPk) {
//        hotelAdvertiseEntityRepository.updateHotelAdvertiseEntityBySignStatus(1, hotelPk);
//
//    }
//
//    // 호텔 광고 거절
//    @Transactional
//    public void updateHotelAdvertiseEntityBySignStatusAndCancelReason(String cancelReason, long hotelPk) {
//        hotelAdvertiseEntityRepository.updateHotelAdvertiseEntityBySignStatusAndCancelReason(0, cancelReason, hotelPk);
//    }
    //호텔 등록 승인
    @Transactional
    public void updateHotelEntityByApproval(long hotelPk) {
        hotelRepository.updateHotelEntityByApproval(1, hotelPk);
    }

    //호텔 중지 신청 승인
    @Transactional
    public void updateHotelSuspendedEntityBySignStatus(long hotelPk) {
        hotelSuspendedRepository.updateHotelSignStatus1(0L,2L, hotelPk);
    }


    //호텔 중지 신청 거절
    @Transactional
    public void updateHotelAdvertiseEntityBySignStatusAndCancel(String suspendedReason, long hotelPk) {
        hotelSuspendedRepository.updateHotelSignStatus(2L, hotelPk);
        hotelSuspendedRepository.updateHotelSuspendedEntityBySuspendedReason(suspendedReason, hotelPk);
    }

    public int maxPage(int columnCount, int rowCount) {
        return (int) Math.ceil((double) columnCount / rowCount);
    }

    //호텔 중지 신청 거절
//    @Transactional
//    public void updateHotelSuspendedEntityBySignStatusAndSuspendedReason( String suspendedReason, long hotelPk){
//        hotelSuspendedRepository.updateHotelSuspendedEntityBySignStatusAndSuspendedReason(0L, suspendedReason, hotelPk);
//    }
}