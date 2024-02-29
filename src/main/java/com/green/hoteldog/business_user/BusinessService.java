package com.green.hoteldog.business_user;

import com.green.hoteldog.business_user.model.*;
import com.green.hoteldog.common.Const;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.common.entity.*;
import com.green.hoteldog.common.entity.composite.HotelOptionComposite;
import com.green.hoteldog.common.entity.jpa_enum.UserRoleEnum;
import com.green.hoteldog.common.repository.*;
import com.green.hoteldog.common.utils.MyFileUtils;
import com.green.hoteldog.common.utils.RandomCodeUtils;
import com.green.hoteldog.exceptions.*;
import com.green.hoteldog.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.green.hoteldog.common.utils.DiscountCostUtil.getDiscountCost;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessService {
    private final AuthenticationFacade authenticationFacade;
    private final BusinessRepository businessRepository;
    private final HotelRepository hotelRepository;
    private final HotelSuspendedRepository suspendedRepository;
    private final HotelRoomRepository hotelRoomRepository;
    private final UserRepository userRepository;
    private final MyFileUtils myFileUtils;
    private final HotelOptionRepository hotelOptionRepository;
    private final HotelOptionInfoRepository hotelOptionInfoRepository;
    private final DogSizeRepository dogSizeRepository;
    private final HotelAdvertiseRepository hotelAdvertiseRepository;
    private final ResComprehensiveInfoRepository resComprehensiveInfoRepository;
    private final ReservationRepository reservationRepository;
    private final HotelResRoomRepository hotelResRoomRepository;
    private final WithdrawalUserRepository withdrawalUserRepository;
    private final PaymentAdRepository paymentAdRepository;

    // 호텔 상태 전환 (1 - 운영 상태, 2 - 운영 중지 상태)
    @Transactional
    public ResVo insHotelStateChange(HotelSateChangeInsDto dto) {
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hotel");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();*/

        long loginIuser = (long) authenticationFacade.getLoginUserPk();
        // 1. 운영 중지 신청
        HotelEntity hotelEntity = hotelRepository.findByHotelPk(loginIuser);
        if (dto.getStateChange() == 1) {
            try {
                HotelSuspendedEntity suspendedEntity = HotelSuspendedEntity.builder()
                        .hotelEntity(hotelEntity)
                        .suspendedReason(dto.getSuspendReason())
                        .build();
                HotelSuspendedEntity savedEntity = suspendedRepository.save(suspendedEntity);
                if (savedEntity == null) {
                    throw new CustomException(HotelErrorCode.UNKNOWN_DATE_FORM); // 멘트 나중에 수정
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new CustomException(HotelErrorCode.UNKNOWN_DATE_FORM); // 멘트 나중에 수정
            }

        } else {
            // 2. 운영 중지 철회 - 호텔 상테 1로 변경
            // 호텔 테이블 상태, 호텔 방 테이블 상태 변경
            try {
                List<Long> hotelPkList = new ArrayList<>();
                hotelPkList.add(hotelEntity.getHotelPk());

                List<HotelRoomInfoEntity> roomInfoEntity = hotelRoomRepository.findAllById(hotelPkList);

                List<HotelRoomInfoEntity> UpdRoomInfoEntity = roomInfoEntity.stream().map(room ->
                        HotelRoomInfoEntity.builder()
                                .roomAble((long) 1)
                                .build()).collect(Collectors.toList());
                List<HotelRoomInfoEntity> savedEntity = hotelRoomRepository.saveAll(UpdRoomInfoEntity);
                if (savedEntity.isEmpty()) {
                    throw new CustomException(HotelErrorCode.UNKNOWN_DATE_FORM); // 멘트 나중에 수정
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new CustomException(HotelErrorCode.UNKNOWN_DATE_FORM); // 멘트 나중에 수정
            }
        }
        return new ResVo(Const.SUCCESS);
    }

    // 광고 신청
    public ResVo postHotelAdvertiseApplication(HotelAdvertiseApplicationDto dto) {
        UserEntity userEntity = userRepository.findById(authenticationFacade.getLoginUserPk()).orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));
        BusinessEntity businessEntity = businessRepository.findByUserEntity(userEntity).orElseThrow(() -> new CustomException(UserErrorCode.NOT_BUSINESS_USER));
        HotelEntity hotelEntity = hotelRepository.findHotelEntityByBusinessEntity(businessEntity).orElseThrow(() -> new CustomException(HotelErrorCode.NOT_EXIST_HOTEL));
        LocalDateTime today = LocalDateTime.now();
        HotelAdvertiseEntity hotelAdvertiseEntity = HotelAdvertiseEntity.builder()
                .hotelEntity(hotelEntity)
                .hotelAdvertiseToDate(today)
                .hotelAdvertiseEndDate(today.plusDays(30L))
                .paymentStatus(1L)
                .adStatus(1L)
                .hotelAdvertiseNum("A" + RandomCodeUtils.getRandomCode(6))
                .build();
        hotelAdvertiseRepository.save(hotelAdvertiseEntity);
        PaymentAdEntity paymentAdEntity = PaymentAdEntity.builder()
                .hotelAdvertiseEntity(hotelAdvertiseEntity)
                .hotelEntity(hotelEntity)
                .paymentStatus(1L)
                .paymentDate(LocalDateTime.now())
                .paymentAdNum("P" + RandomCodeUtils.getRandomCode(6))
                .cardNum(dto.getCardNum())
                .cardValidThru(dto.getCardValidThru())
                .cardUserName(dto.getCardUserName())
                .userBirth(dto.getUserBirth())
                .paymentStatus(1L)
                .build();
        paymentAdRepository.save(paymentAdEntity);

        return new ResVo(1);
    }

    // 예약 리스트 출력
    public List<ReservationListSelVo> getHotelReservationList() {
        UserEntity userEntity = userRepository.findById(authenticationFacade.getLoginUserPk()).orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));
        BusinessEntity businessEntity = businessRepository.findByUserEntity(userEntity).orElseThrow(() -> new CustomException(UserErrorCode.NOT_BUSINESS_USER));
        HotelEntity hotelEntity = hotelRepository.findHotelEntityByBusinessEntity(businessEntity).orElseThrow(() -> new CustomException(HotelErrorCode.NOT_EXIST_HOTEL));
        List<ReservationEntity> reservationEntityList = reservationRepository.findAllByHotelEntity(hotelEntity);
        return null;

    }
    // 영웅
    // ---------------------------------------------------------------------------------------------------
    //재웅

    //사업자 유저 호텔 등록
    @Transactional
    public ResVo insHotel(HotelInsDto dto) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(authenticationFacade.getLoginUserPk());
        UserEntity userEntity = optionalUserEntity.orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));


        Optional<BusinessEntity> optionalBusinessEntity = businessRepository.findByUserEntity(userEntity);
        BusinessEntity businessEntity = optionalBusinessEntity.orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));

        HotelEntity hotelEntity = HotelEntity.builder()
                .hotelNm(dto.getHotelNm())
                .businessEntity(businessEntity)
                .hotelDetailInfo(dto.getHotelDetailInfo())
                .businessNum(dto.getBusinessNum())
                .hotelCall(dto.getHotelCall())
                .hotelFullAddress(dto.getHotelAddressInfo().getAddressName() + " " + dto.getHotelAddressInfo().getDetailAddress())
                .advertise(0L)
                .approval(0L)
                .signStatus(0L)
                .hotelNum("H" + RandomCodeUtils.getRandomCode(6))
                .build();
        String target = "/manager/hotel/" + hotelEntity.getHotelPk();
        String hotelCertificationFile = myFileUtils.transferTo(dto.getBusinessCertificationFile(), target);
        hotelEntity.setBusinessCertificate(hotelCertificationFile);
        hotelRepository.save(hotelEntity);
        HotelWhereEntity hotelWhereEntity = HotelWhereEntity.builder()
                .x(dto.getHotelAddressInfo().getX())
                .y(dto.getHotelAddressInfo().getY())
                .addressName(dto.getHotelAddressInfo().getAddressName())
                .zoneNum(dto.getHotelAddressInfo().getZoneNum())
                .detailAddress(dto.getHotelAddressInfo().getDetailAddress())
                .region1DepthName(dto.getHotelAddressInfo().getRegion1DepthName())
                .region2DepthName(dto.getHotelAddressInfo().getRegion2DepthName())
                .region3DepthName(dto.getHotelAddressInfo().getRegion3DepthName())
                .hotelEntity(hotelEntity)
                .build();
        hotelEntity.setHotelWhereEntity(hotelWhereEntity);


        List<HotelPicEntity> hotelPicEntityList = new ArrayList<>();
        for (MultipartFile file : dto.getHotelPics()) {
            target = "/hotel/" + hotelEntity.getHotelPk();
            String hotelPicFile = myFileUtils.transferTo(file, target);
            HotelPicEntity hotelPicsEntity = HotelPicEntity.builder()
                    .hotelEntity(hotelEntity)
                    .pic(hotelPicFile)
                    .build();
            hotelPicEntityList.add(hotelPicsEntity);
        }
        hotelEntity.setHotelPicEntity(hotelPicEntityList);

        List<HotelOptionEntity> hotelOptionEntityList = hotelOptionRepository.findAllById(dto.getHotelOption());

        List<HotelOptionInfoEntity> hotelOptionInfoEntityList = new ArrayList<>();
        for (HotelOptionEntity hotelOptionEntity : hotelOptionEntityList) {
            HotelOptionComposite hotelOptionComposite = HotelOptionComposite.builder()
                    .hotelPk(hotelEntity.getHotelPk())
                    .optionPk(hotelOptionEntity.getOptionPk()).build();

            HotelOptionInfoEntity hotelOptionInfoEntity = HotelOptionInfoEntity.builder()
                    .composite(hotelOptionComposite)
                    .hotelEntity(hotelEntity)
                    .hotelOptionEntity(hotelOptionEntity)
                    .build();
            hotelOptionInfoEntityList.add(hotelOptionInfoEntity);
        }
        hotelOptionInfoRepository.saveAll(hotelOptionInfoEntityList);

        return new ResVo(1);
    }

    //사업자 유저가 등록한 호텔 정보
    @Transactional
    public BusinessUserHotelVo getBusinessUserHotel() {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(authenticationFacade.getLoginUserPk());
        UserEntity userEntity = optionalUserEntity.orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));


        Optional<BusinessEntity> optionalBusinessEntity = businessRepository.findByUserEntity(userEntity);
        BusinessEntity businessEntity = optionalBusinessEntity.orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));

        Optional<HotelEntity> optionalHotelEntity = hotelRepository.findHotelEntityByBusinessEntity(businessEntity);
        HotelEntity hotelEntity = new HotelEntity();
        BusinessUserHotelVo businessUserHotelVo = new BusinessUserHotelVo();

        if (optionalHotelEntity.isPresent()) {
            hotelEntity = optionalHotelEntity.get();
            List<HotelOptionInfoEntity> hotelOptionInfoEntityList = hotelOptionInfoRepository.findAllByHotelEntity(hotelEntity);
            List<Long> hotelOptionPkList = new ArrayList<>();

            List<HotelOptionInfoVo> hotelOptionInfoVoList = new ArrayList<>();
            /*List<HotelOptionInfoVo> hotelOptionInfoVoList1 = hotelOptionInfoRepository.getHotelOptionInfoList(hotelEntity.getHotelOptionInfoEntity());*/

            for (HotelOptionInfoEntity hotelOptionInfoEntity : hotelOptionInfoEntityList) {
                HotelOptionInfoVo vo = HotelOptionInfoVo.builder()
                        .optionPk(hotelOptionInfoEntity.getHotelOptionEntity().getOptionPk())
                        .optionNm(hotelOptionInfoEntity.getHotelOptionEntity().getOptionNm())
                        .build();
                hotelOptionInfoVoList.add(vo);
            }
            List<HotelRoomInfoEntity> hotelRoomInfoEntityList = hotelRoomRepository.findHotelRoomInfoEntitiesByHotelEntity(hotelEntity);
            Optional<HotelAdvertiseEntity> optionalHotelAdvertiseEntity = hotelAdvertiseRepository.findByHotelEntity(hotelEntity);
            if (optionalHotelAdvertiseEntity.isPresent()) {
                HotelAdvertiseEntity hotelAdvertiseEntity = optionalHotelAdvertiseEntity.get();
                businessUserHotelVo.setHotelAdvertiseToDate(hotelAdvertiseEntity.getHotelAdvertiseToDate().toString());
                businessUserHotelVo.setHotelAdvertiseEndDate(hotelAdvertiseEntity.getHotelAdvertiseEndDate().toString());
            }
            businessUserHotelVo = BusinessUserHotelVo.builder()
                    .hotelPk(hotelEntity.getHotelPk())
                    .hotelNm(hotelEntity.getHotelNm())
                    .hotelDetailInfo(hotelEntity.getHotelDetailInfo())
                    .businessNum(hotelEntity.getBusinessNum())
                    .hotelCall(hotelEntity.getHotelCall())
                    .hotelNum(hotelEntity.getHotelNum())
                    .advertise(hotelEntity.getAdvertise())
                    .createdAt(hotelEntity.getCreatedAt().toString())
                    .hotelFullAddress(hotelEntity.getHotelWhereEntity().getAddressName() + " " + hotelEntity.getHotelWhereEntity().getDetailAddress())
                    .hotelPics(hotelEntity.getHotelPicEntity().stream().map(HotelPicEntity::getPic).collect(Collectors.toList()))
                    .optionList(hotelOptionInfoVoList)
                    .businessCertificate(hotelEntity.getBusinessCertificate())
                    .hotelAddressInfo(HotelAddressInfo.builder()
                            .hotelPk(hotelEntity.getHotelPk())
                            .x(hotelEntity.getHotelWhereEntity().getX())
                            .y(hotelEntity.getHotelWhereEntity().getY())
                            .addressName(hotelEntity.getHotelWhereEntity().getAddressName())
                            .zoneNum(hotelEntity.getHotelWhereEntity().getZoneNum())
                            .detailAddress(hotelEntity.getHotelWhereEntity().getDetailAddress())
                            .region1DepthName(hotelEntity.getHotelWhereEntity().getRegion1DepthName())
                            .region2DepthName(hotelEntity.getHotelWhereEntity().getRegion2DepthName())
                            .region3DepthName(hotelEntity.getHotelWhereEntity().getRegion3DepthName())
                            .build())
                    .hotelRoomInfoList(hotelRoomInfoEntityList.stream().map(hotelRoomInfoEntity -> HotelRoomInfo.builder()
                            .hotelRoomPk(hotelRoomInfoEntity.getHotelRoomPk())
                            .hotelRoomNm(hotelRoomInfoEntity.getHotelRoomNm())
                            .roomAble(hotelRoomInfoEntity.getRoomAble())
                            .hotelRoomCost(getDiscountCost(hotelRoomInfoEntity.getHotelRoomCost(), hotelRoomInfoEntity.getDiscountPer()))
                            .hotelRoomEa(hotelRoomInfoEntity.getHotelRoomEa())
                            .roomPic(hotelRoomInfoEntity.getRoomPic())
                            .maximum(hotelRoomInfoEntity.getMaximum())
                            .discountPer(hotelRoomInfoEntity.getDiscountPer())
                            .createdAt(hotelRoomInfoEntity.getCreatedAt().toString())
                            .build()).collect(Collectors.toList())
                    ).build();
        }
        log.info("businessUserHotelVo : " + businessUserHotelVo);

        return businessUserHotelVo;
    }

    //사업자 유저가 등록한 호텔 방 수정
    @Transactional
    public ResVo putHotelRoomInfo(BusinessHotelRoomPutDto dto) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(authenticationFacade.getLoginUserPk());
        UserEntity userEntity = optionalUserEntity.orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));


        Optional<BusinessEntity> optionalBusinessEntity = businessRepository.findByUserEntity(userEntity);
        BusinessEntity businessEntity = optionalBusinessEntity.orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));

        Optional<HotelRoomInfoEntity> optionalHotelRoomInfoEntity = hotelRoomRepository.findById(dto.getHotelRoomPk());

        HotelRoomInfoEntity hotelRoomInfoEntity = optionalHotelRoomInfoEntity.orElseThrow(() -> new CustomException(HotelErrorCode.NOT_EXIST_HOTEL_ROOM));
        String hotelRoomPic = null;
        if (dto.getRoomPic() != null && !dto.getRoomPic().isEmpty()) {
            String target = "/room/" + hotelRoomInfoEntity.getHotelEntity().getHotelPk() + "/" + hotelRoomInfoEntity.getHotelRoomPk();
            myFileUtils.delFiles(target);
            hotelRoomPic = myFileUtils.transferTo(dto.getRoomPic(), target);
            hotelRoomInfoEntity.setRoomPic(hotelRoomPic);
        }
        long changeRoomEa = dto.getHotelRoomEa() - hotelRoomInfoEntity.getHotelRoomEa();
        hotelRoomInfoEntity.setHotelRoomNm(dto.getHotelRoomNm());
        hotelRoomInfoEntity.setHotelRoomCost(dto.getHotelRoomCost());
        hotelRoomInfoEntity.setHotelRoomEa(dto.getHotelRoomEa());
        hotelRoomInfoEntity.setMaximum(dto.getMaximum());
        hotelRoomInfoEntity.setDogSizeEntity(dogSizeRepository.getReferenceById(dto.getSizePk()));
        hotelResRoomRepository.findAllByHotelRoomInfoEntity(hotelRoomInfoEntity).forEach(hotelResRoomEntity -> {
            if (hotelResRoomEntity.getRoomLeftEa() + changeRoomEa < 0) {
                hotelResRoomEntity.setRoomLeftEa(0L);
            } else {
                hotelResRoomEntity.setRoomLeftEa(hotelResRoomEntity.getRoomLeftEa() + changeRoomEa);
            }
        });

        return new ResVo(1);
    }

    // 사업자 유저 예약 강아지 방 정보
    @Transactional
    public List<HotelRoomAndDogInfoVo> getHotelRoomAndDogInfo(long resPk) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(authenticationFacade.getLoginUserPk());
        UserEntity userEntity = optionalUserEntity.orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));


        Optional<BusinessEntity> optionalBusinessEntity = businessRepository.findByUserEntity(userEntity);
        BusinessEntity businessEntity = optionalBusinessEntity.orElseThrow(() -> new CustomException(UserErrorCode.NOT_BUSINESS_USER));
        Optional<ReservationEntity> optionalReservationEntity = reservationRepository.findById(resPk);
        ReservationEntity reservationEntity = optionalReservationEntity.orElseThrow(() -> new CustomException(ReservationErrorCode.NOT_EXIST_RESERVATION));
        List<ResComprehensiveInfoEntity> resComprehensiveInfoEntityList = resComprehensiveInfoRepository.findAllByReservationEntity(reservationEntity);
        List<HotelRoomAndDogInfoVo> hotelRoomAndDogInfoVoList = new ArrayList<>();
        for (ResComprehensiveInfoEntity resComprehensiveInfoEntity : resComprehensiveInfoEntityList) {
            HotelRoomInfoEntity hotelRoomInfoEntity = resComprehensiveInfoEntity.getHotelRoomInfoEntity();
            ResDogInfoEntity resDogInfoEntity = resComprehensiveInfoEntity.getResDogInfoEntity();
            ResDogInfo resDogInfo = ResDogInfo.builder()
                    .dogResDogPk(resDogInfoEntity.getResDogPk())
                    .dogNm(resDogInfoEntity.getDogNm())
                    .dogAge(resDogInfoEntity.getAge())
                    .sizePk(resDogInfoEntity.getDogSizeEntity().getSizePk())
                    .information(resDogInfoEntity.getInformation())
                    .build();
            ResRoomInfo resRoomInfo = ResRoomInfo.builder()
                    .hotelRoomPk(hotelRoomInfoEntity.getHotelRoomPk())
                    .hotelPk(hotelRoomInfoEntity.getHotelEntity().getHotelPk())
                    .sizePk(hotelRoomInfoEntity.getDogSizeEntity().getSizePk())
                    .hotelRoomNm(hotelRoomInfoEntity.getHotelRoomNm())
                    .roomPic(hotelRoomInfoEntity.getRoomPic()).build();
            hotelRoomAndDogInfoVoList.add(HotelRoomAndDogInfoVo.builder()
                    .resRoomInfo(resRoomInfo)
                    .resDogInfo(resDogInfo)
                    .build());
        }
        return hotelRoomAndDogInfoVoList;
    }

    @Transactional
    public ResVo toggleHotelRoomActive(long hotelRoomPk) {
        UserEntity userEntity = userRepository.findById(authenticationFacade.getLoginUserPk()).orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));
        BusinessEntity businessEntity = businessRepository.findByUserEntity(userEntity).orElseThrow(() -> new CustomException(UserErrorCode.NOT_BUSINESS_USER));
        HotelRoomInfoEntity hotelRoomInfoEntity = hotelRoomRepository.findById(hotelRoomPk).orElseThrow(() -> new CustomException(HotelErrorCode.NOT_EXIST_HOTEL_ROOM));
        if (hotelRoomInfoEntity.getHotelRoomEa() == null || hotelRoomInfoEntity.getHotelRoomCost() == null || hotelRoomInfoEntity.getMaximum() == null || hotelRoomInfoEntity.getRoomPic() == null) {
            throw new CustomException(HotelErrorCode.REQUIRED_VALUE_IS_NULL);
        }
        if (hotelRoomInfoEntity.getHotelEntity().getBusinessEntity().getBusinessPk() != businessEntity.getBusinessPk()) {
            throw new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED);
        }
        if (hotelRoomInfoEntity.getRoomAble() == 1) {
            hotelRoomInfoEntity.setRoomAble(0L);
            return new ResVo(2);
        }
        if (hotelRoomInfoEntity.getRoomAble() == 0) {
            hotelRoomInfoEntity.setRoomAble(1L);
            return new ResVo(1);
        }
        return null;
    }

    @Transactional
    public ResVo postBusinessUserWithdrawal(String upw) {
        UserEntity userEntity = userRepository.findById(authenticationFacade.getLoginUserPk()).orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));
        if (!userEntity.getUpw().equals(upw)) {
            throw new CustomException(UserErrorCode.MISS_MATCH_PASSWORD);
        }
        BusinessEntity businessEntity = businessRepository.findByUserEntity(userEntity).orElseThrow(() -> new CustomException(UserErrorCode.NOT_BUSINESS_USER));
        HotelEntity hotelEntity = hotelRepository.findHotelEntityByBusinessEntity(businessEntity).orElseThrow(() -> new CustomException(HotelErrorCode.NOT_EXIST_HOTEL));
        if (!reservationRepository.findAllByHotelEntityAndResStatusLessThan(hotelEntity, 2L).isEmpty()) {
            throw new CustomException(WithdrawalErrorCode.NOT_CHECK_OUT_RESERVATIONS_REMAIN);
        }


        LocalDateTime today = LocalDateTime.now();
        WithdrawalUserEntity withdrawalUserEntity = WithdrawalUserEntity.builder()
                .userEntity(userEntity)
                .approvalDate(today)
                .applyDate(today.plusDays(30))
                .build();
        withdrawalUserRepository.save(withdrawalUserEntity);
        userEntity.setUserStatus(1L);
        userEntity.setUserRole(UserRoleEnum.WITHDRAWAL);


        return new ResVo(1);
    }

    //재웅
    // ---------------------------------------------------------------------------------------------------
    //승준
    //승준
}
