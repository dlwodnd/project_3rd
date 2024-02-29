package com.green.hoteldog.business_user;

import com.green.hoteldog.business_user.model.*;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.common.entity.*;
import com.green.hoteldog.common.entity.composite.HotelOptionComposite;
import com.green.hoteldog.common.entity.jpa_enum.UserRoleEnum;
import com.green.hoteldog.common.repository.*;
import com.green.hoteldog.common.utils.MyFileUtils;
import com.green.hoteldog.common.utils.RandomCodeUtils;
import com.green.hoteldog.exceptions.*;
import com.green.hoteldog.security.AuthenticationFacade;
import com.green.hoteldog.user.models.HotelRoomDateProcDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private final ResPaymentRepository resPaymentRepository;
    private final HotelPicRepository hotelPicRepository;
    private final RefundRepository refundRepository;


    // 호텔 상태 전환
    @Transactional
    public ResVo insHotelStateChange(HotelSateChangeInsDto dto) {
        UserEntity userEntity = userRepository.findById(authenticationFacade.getLoginUserPk()).orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));
        BusinessEntity businessEntity = businessRepository.findByUserEntity(userEntity).orElseThrow(() -> new CustomException(UserErrorCode.NOT_BUSINESS_USER));
        HotelEntity hotelEntity = hotelRepository.findHotelEntityByBusinessEntity(businessEntity).orElseThrow(() -> new CustomException(HotelErrorCode.NOT_EXIST_HOTEL));
        if(hotelEntity.getApproval() == 0){
            throw new CustomException(HotelErrorCode.WAITING_SUSPEND_APPROVAL);
        }else if (hotelEntity.getApproval() == 1 && hotelEntity.getSignStatus() == 1) {
            throw new CustomException(HotelErrorCode.WAITING_SUSPEND_APPROVAL);
        }else if (hotelEntity.getApproval() == 2){
            hotelEntity.setApproval(1L);
            return new ResVo(2);
        }else {
            hotelEntity.setSignStatus(1L);
            HotelSuspendedEntity hotelSuspendedEntity = HotelSuspendedEntity.builder()
                    .hotelEntity(hotelEntity)
                    .suspendedReason(dto.getSuspendReason())
                    .build();
            suspendedRepository.save(hotelSuspendedEntity);
            return new ResVo(1);
        }


        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hotel");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();*//*

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
        }*/

    }

    // 광고 신청
    @Transactional
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
                .hotelAdvertiseNum("A" + RandomCodeUtils.getRandomCode(5))
                .build();
        hotelAdvertiseRepository.save(hotelAdvertiseEntity);
        PaymentAdEntity paymentAdEntity = PaymentAdEntity.builder()
                .hotelAdvertiseEntity(hotelAdvertiseEntity)
                .hotelEntity(hotelEntity)
                .paymentStatus(1L)
                .paymentDate(LocalDateTime.now())
                .paymentAdNum("Q" + RandomCodeUtils.getRandomCode(5))
                .cardNum(dto.getCardNum())
                .cardValidThru(dto.getCardValidThru())
                .cardUserName(dto.getCardUserName())
                .userBirth(dto.getUserBirth())
                .paymentAmount(50000L)
                .paymentStatus(1L)
                .build();
        paymentAdRepository.save(paymentAdEntity);

        return new ResVo(1);
    }

    // 예약 리스트 출력
    @Transactional
    public List<ReservationListSelVo> getHotelReservationList(Pageable pageable) {
        UserEntity userEntity = userRepository.findById(authenticationFacade.getLoginUserPk()).orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));
        BusinessEntity businessEntity = businessRepository.findByUserEntity(userEntity).orElseThrow(() -> new CustomException(UserErrorCode.NOT_BUSINESS_USER));
        HotelEntity hotelEntity = hotelRepository.findHotelEntityByBusinessEntity(businessEntity).orElseThrow(() -> new CustomException(HotelErrorCode.NOT_EXIST_HOTEL));
        List<ReservationEntity> reservationEntityList = reservationRepository.findAllByHotelEntity(hotelEntity);


        return reservationEntityList.isEmpty() ? new ArrayList<>() : reservationEntityList.stream().map(item -> {
            ResPaymentEntity resPaymentEntity = resPaymentRepository.findByReservationEntity(item);
            return ReservationListSelVo.builder()
                    .resPk(item.getResPk())
                    .resNum(item.getResNum())
                    .resStatus(item.getResStatus())
                    .nickNm(item.getUserEntity().getNickname())
                    .hotelNm(item.getHotelEntity().getHotelNm())
                    .fromDate(item.getFromDate().toString())
                    .toDate(item.getToDate().toString())
                    .userPhoneNum(item.getUserEntity().getPhoneNum())
                    .payment(resPaymentEntity.getPaymentAmount())
                    .build();
        }).collect(Collectors.toList());

    }
    //하루 방 예약정보
    @Transactional
    public List<ReservationTodayInfoVo> getHotelReservationTodayList() {
        /*UserEntity userEntity = userRepository.findById(authenticationFacade.getLoginUserPk()).orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));
        BusinessEntity businessEntity = businessRepository.findByUserEntity(userEntity).orElseThrow(() -> new CustomException(UserErrorCode.NOT_BUSINESS_USER));
        HotelEntity hotelEntity = hotelRepository.findHotelEntityByBusinessEntity(businessEntity).orElseThrow(() -> new CustomException(HotelErrorCode.NOT_EXIST_HOTEL));
        List<ReservationEntity> reservationEntityList = reservationRepository.findAllByHotelEntityAndFromDate(hotelEntity, LocalDate.now());
        List<HotelRoomInfoEntity> hotelRoomInfoEntityList = hotelRoomRepository.findAllByHotelEntityOrderByHotelResRoomEntitiesAsc(hotelEntity);
        hotelRoomInfoEntityList.stream().map(item -> {
            List<HotelResRoomEntity> hotelResRoomEntityList = hotelResRoomRepository.findAllByHotelRoomInfoEntity(item);
            return hotelResRoomEntityList.stream().map(hotelResRoomEntity -> {
                if (hotelResRoomEntity.getRoomLeftEa() < 5) {
                    return ReservationTodayInfoVo.builder()
                            .resPk(hotelResRoomEntity.getReservationEntity().getResPk())
                            .resNum(hotelResRoomEntity.getReservationEntity().getResNum())
                            .nickNm(hotelResRoomEntity.getReservationEntity().getUserEntity().getNickname())
                            .hotelRoomPk(hotelResRoomEntity.getHotelRoomInfoEntity().getHotelRoomPk())
                            .hotelRoomNm(hotelResRoomEntity.getHotelRoomInfoEntity().getHotelRoomNm())
                            .resDogPk(hotelResRoomEntity.getResDogInfoEntity().getResDogPk())
                            .fromDate(hotelResRoomEntity.getReservationEntity().getFromDate().toString())
                            .toDate(hotelResRoomEntity.getReservationEntity().getToDate().toString())
                            .userPhoneNum(hotelResRoomEntity.getReservationEntity().getUserEntity().getPhoneNum())
                            .payment(hotelResRoomEntity.getResPaymentEntity().getPaymentAmount())
                            .resStatus(hotelResRoomEntity.getReservationEntity().getResStatus())
                            .build();
                }
                return null;
            }).filter(Objects::nonNull).collect(Collectors.toList());
        }).collect(Collectors.toList());*/


        return null;
    }
    //사업자 유저 호텔 정보 수정
    @Transactional
    public ResVo putHotel(HotelUpdateDto dto){
        UserEntity userEntity = userRepository.findById(authenticationFacade.getLoginUserPk()).orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));
        BusinessEntity businessEntity = businessRepository.findByUserEntity(userEntity).orElseThrow(() -> new CustomException(UserErrorCode.NOT_BUSINESS_USER));
        HotelEntity hotelEntity = hotelRepository.findHotelEntityByBusinessEntity(businessEntity).orElseThrow(() -> new CustomException(HotelErrorCode.NOT_EXIST_HOTEL));
        hotelEntity.setHotelDetailInfo(dto.getHotelDetailInfo());
        hotelOptionInfoRepository.deleteAllByHotelEntity(hotelEntity);
        for(Long optionPk : dto.getOptionList()){
            HotelOptionComposite hotelOptionComposite = HotelOptionComposite.builder()
                    .hotelPk(hotelEntity.getHotelPk())
                    .optionPk(optionPk)
                    .build();
            HotelOptionInfoEntity hotelOptionInfoEntity = HotelOptionInfoEntity.builder()
                    .composite(hotelOptionComposite)
                    .hotelEntity(hotelEntity)
                    .hotelOptionEntity(hotelOptionRepository.getReferenceById(optionPk))
                    .build();
            hotelOptionInfoRepository.save(hotelOptionInfoEntity);
        }

        if(dto.getHotelPics() != null && !dto.getHotelPics().isEmpty()){

            String target = "/hotel/" + hotelEntity.getHotelPk();
            myFileUtils.delFiles(target);
            hotelPicRepository.deleteAllByHotelEntity(hotelEntity);

            for (MultipartFile file : dto.getHotelPics()) {
                String hotelPicFile = myFileUtils.transferTo(file, target);
                HotelPicEntity hotelPicsEntity = HotelPicEntity.builder()
                        .hotelEntity(hotelEntity)
                        .pic(hotelPicFile)
                        .build();
                hotelPicRepository.save(hotelPicsEntity);
            }
        }
        return new ResVo(1);
    }

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
                .hotelNum("H" + RandomCodeUtils.getRandomCode(5))
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
        hotelRoomInfoEntity.setDiscountPer(dto.getDiscountPer());
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
    //호텔 방 활성화 & 비활성화 토글
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

    //사업자 유저 회원탈퇴
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
    public ResVo reservationApproval(List<Long> resPkList){
        UserEntity userEntity = userRepository.findById(authenticationFacade.getLoginUserPk()).orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));
        BusinessEntity businessEntity = businessRepository.findByUserEntity(userEntity).orElseThrow(() -> new CustomException(UserErrorCode.NOT_BUSINESS_USER));
        HotelEntity hotelEntity = hotelRepository.findHotelEntityByBusinessEntity(businessEntity).orElseThrow(() -> new CustomException(HotelErrorCode.NOT_EXIST_HOTEL));
        List<ReservationEntity> reservationEntityList = reservationRepository.findAllById(resPkList);
        reservationEntityList.forEach(reservationEntity -> {
            if(!Objects.equals(reservationEntity.getHotelEntity().getHotelPk(), hotelEntity.getHotelPk())){
                throw new CustomException(ReservationErrorCode.NOT_EXIST_RESERVATION);
            }
            if(reservationEntity.getResStatus() != 0){
                throw new CustomException(ReservationErrorCode.NOT_WAITING_APPROVAL);
            }
            reservationEntity.setResStatus(1L);
        });
        return new ResVo(1);
    }
    public ResVo reservationCancel(ResCancelDto dto){
        UserEntity userEntity = userRepository.findById(authenticationFacade.getLoginUserPk()).orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));
        BusinessEntity businessEntity = businessRepository.findByUserEntity(userEntity).orElseThrow(() -> new CustomException(UserErrorCode.NOT_BUSINESS_USER));
        HotelEntity hotelEntity = hotelRepository.findHotelEntityByBusinessEntity(businessEntity).orElseThrow(() -> new CustomException(HotelErrorCode.NOT_EXIST_HOTEL));
        ReservationEntity reservationEntity = reservationRepository.findById(dto.getResPk())
                .orElseThrow(() -> new CustomException(ReservationErrorCode.NOT_EXIST_RESERVATION));
        if(!reservationEntity.getHotelEntity().getHotelPk().equals(hotelEntity.getHotelPk())){
            throw new CustomException(ReservationErrorCode.NOT_EXIST_RESERVATION);
        }else if(reservationEntity.getResStatus() != 0){
            throw new CustomException(ReservationErrorCode.NOT_CANCELABLE);
        }else {
            reservationEntity.setResStatus(5L);
            RefundEntity refundEntity = RefundEntity.builder()
                    .userEntity(reservationEntity.getUserEntity())
                    .reservationEntity(reservationEntity)
                    .refundAmount(reservationEntity.getResPaymentEntity().getPaymentAmount())
                    .refundDate(LocalDateTime.now())
                    .refundNum("R" + RandomCodeUtils.getRandomCode(5))
                    .build();
            refundRepository.save(refundEntity);

            reservationEntity.getResPaymentEntity().setPaymentStatus(2L);
            ResComprehensiveInfoEntity resComprehensiveInfoEntity = resComprehensiveInfoRepository.findByReservationEntity(reservationEntity);
            HotelRoomDateProcDto hotelRoomDateProcDto = HotelRoomDateProcDto.builder()
                    .hotelRoomInfoEntity(resComprehensiveInfoEntity.getHotelRoomInfoEntity())
                    .fromDate(reservationEntity.getFromDate())
                    .toDate(reservationEntity.getToDate())
                    .build();

            hotelResRoomRepository.updateHotelResRoomRefundCount(hotelRoomDateProcDto);
            return new ResVo(1);
        }


    }
    public ResVo reservationCheckIn(List<Long> resPkList){
        return null;
    }
    public ResVo reservationCheckOut(List<Long> resPkList){
        return null;
    }

}
