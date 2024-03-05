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
    private final UserRepository userRepository;
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

        /*List<BusinessEntity> users = businessEntityRepository.findByUserEntity_UserPkInOrderByCreatedAtDesc(userPks, pageable);
        return users.stream()
                .map(BusinessEntity -> UserListVo.BusinessVo.UserList(BusinessEntity))
                .collect(Collectors.toList());*/
        return null;
    }

    public UserInfoVo getUsers(Pageable pageable){
        UserInfo vo2 = new UserInfo();
        long totalRecords = userRepository.count();
        int maxPage = (int) Math.ceil((double) totalRecords / pageable.getPageSize());
        if(maxPage == 0){
            maxPage = 1;
        }
        List<UserEntity> users = userRepository.findAll(pageable).getContent();
        List<UserInfo> userListVos = users.stream()
                .map(UserInfo::UserList2)
                .toList();
        UserInfoVo userInfoVo = UserInfoVo.builder()
                .totalPage(userRepository.findAll(pageable).getTotalPages())
                .userInfoList(userListVos)
                .build();
        return userInfoVo;
    }

    public BusinessUserInfoVo businessUsers(Pageable pageable){
        long totalRecords = businessEntityRepository.count();
        List<BusinessEntity> businessEntities = businessEntityRepository.findAllByRole(UserRoleEnum.BUSINESS_USER, pageable).getContent();

        int maxPage = (int) Math.ceil((double) totalRecords / pageable.getPageSize());
        if(maxPage == 0){
            maxPage = 1;
        }
        return BusinessUserInfoVo.builder()
                .totalPage(businessEntityRepository.findAllByRole(UserRoleEnum.BUSINESS_USER, pageable).getTotalPages())
                .businessUserInfoList(businessEntities.stream()
                        .map(item -> BusinessUserInfo.builder()
                                .businessUserPk(item.getBusinessPk())
                                .userEmail(item.getBusinessEmail())
                                .phoneNum(item.getBusinessPhoneNum())
                                .businessName(item.getBusinessName())
                                .hotelAddress(item.getHotelEntity().getHotelFullAddress())
                                .build()).collect(Collectors.toList())

                ).build();
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