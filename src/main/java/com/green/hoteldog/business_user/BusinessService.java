package com.green.hoteldog.business_user;

import com.green.hoteldog.business_user.model.*;
import com.green.hoteldog.common.Const;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.common.entity.*;
import com.green.hoteldog.common.entity.composite.HotelOptionComposite;
import com.green.hoteldog.common.repository.*;
import com.green.hoteldog.common.utils.MyFileUtils;
import com.green.hoteldog.common.utils.RandomCodeUtils;
import com.green.hoteldog.exceptions.AuthorizedErrorCode;
import com.green.hoteldog.exceptions.CustomException;
import com.green.hoteldog.exceptions.HotelErrorCode;
import com.green.hoteldog.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    // 호텔 상태 전환 (1 - 운영 상태, 2 - 운영 중지 상태)
    @Transactional
    public ResVo insHotelStateChange(HotelSateChangeInsDto dto){
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hotel");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();*/

        long loginIuser = (long) authenticationFacade.getLoginUserPk();
        // 1. 운영 중지 신청
        HotelEntity hotelEntity = hotelRepository.findByHotelPk(loginIuser);
        if(dto.getStateChange() == 1){
            try {
                HotelSuspendedEntity suspendedEntity = HotelSuspendedEntity.builder()
                        .hotelEntity(hotelEntity)
                        .suspendedReason(dto.getSuspendReason())
                        .build();
                HotelSuspendedEntity savedEntity = suspendedRepository.save(suspendedEntity);
                if (savedEntity == null){
                    throw new CustomException(HotelErrorCode.UNKNOWN_DATE_FORM); // 멘트 나중에 수정
                }
            }catch (Exception e){
                e.printStackTrace();
                throw new CustomException(HotelErrorCode.UNKNOWN_DATE_FORM); // 멘트 나중에 수정
            }

        }else{
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
                if (savedEntity.size() == 0){
                    throw new CustomException(HotelErrorCode.UNKNOWN_DATE_FORM); // 멘트 나중에 수정
                }
            }catch (Exception e){
                e.printStackTrace();
                throw new CustomException(HotelErrorCode.UNKNOWN_DATE_FORM); // 멘트 나중에 수정
            }
        }
        return new ResVo(Const.SUCCESS);
    }

    // 광고 신청
    public ResVo postHotelAdvertiseApplication(HotelAdvertiseApplicationDto dto){
        // 1. 유저가 사업자유저인지 체크
        dto.builder().userPk(authenticationFacade.getLoginUserPk()).build();

        // 2. 광고테이블 인서트, 결제 테이블 인서트


        // 3. 결제 완료 후 광고테이블 결제여부 업데이트


        return new ResVo(0);
    }

    // 호텔 방 등록
    public ResVo postHotelRoom(List<HotelRoomInsDto> dto){
        // 1. 해당 호텔이 있는지 체크

        // 2. 등록
        return new ResVo(0);
    }

    // 예약 리스트 출력
    public List<ReservaionListSelVo> getHotelReservationList(int hotelPk){
        // 1. 최신순으로 예약 리스트 출력
        // 2. 검색 기능(예약번호, 닉네임)
        // 3. 필터링 기능(추후 논의)
        return null;
    }
    // 영웅
    // ---------------------------------------------------------------------------------------------------
    //재웅

    //사업자 유저 호텔 등록
    @Transactional
    public ResVo insHotel(HotelInsDto dto){
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
                .advertise(0L)
                .approval(0L)
                .signStatus(0L)
                .hotelNum("H" + RandomCodeUtils.getRandomCode(6))
                .build();
        String target = "/manager/hotel/" + hotelEntity.getHotelPk();
        String hotelCertificationFile = myFileUtils.transferTo(dto.getBusinessCertificationFile(),target);
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
        for(MultipartFile file : dto.getHotelPics()){
            target = "/hotel/" + hotelEntity.getHotelPk();
            String hotelPicFile = myFileUtils.transferTo(file,target);
            HotelPicEntity hotelPicsEntity = HotelPicEntity.builder()
                    .hotelEntity(hotelEntity)
                    .pic(hotelPicFile)
                    .build();
            hotelPicEntityList.add(hotelPicsEntity);
        }
        hotelEntity.setHotelPicEntity(hotelPicEntityList);

        List<HotelOptionEntity> hotelOptionEntityList = hotelOptionRepository.findAllById(dto.getHotelOption());

        List<HotelOptionInfoEntity> hotelOptionInfoEntityList = new ArrayList<>();
        for(HotelOptionEntity hotelOptionEntity : hotelOptionEntityList){
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
    public BusinessUserHotelVo getBusinessUserHotel(){
        Optional<UserEntity> optionalUserEntity = userRepository.findById(authenticationFacade.getLoginUserPk());
        UserEntity userEntity = optionalUserEntity.orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));


        Optional<BusinessEntity> optionalBusinessEntity = businessRepository.findByUserEntity(userEntity);
        BusinessEntity businessEntity = optionalBusinessEntity.orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));

        Optional<HotelEntity> optionalHotelEntity = hotelRepository.findHotelEntityByBusinessEntity(businessEntity);
        HotelEntity hotelEntity = new HotelEntity();
        BusinessUserHotelVo businessUserHotelVo = new BusinessUserHotelVo();

        if(optionalHotelEntity.isPresent()){
            hotelEntity = optionalHotelEntity.get();
            List<HotelOptionInfoEntity> hotelOptionInfoEntityList = hotelOptionInfoRepository.findAllByHotelEntity(hotelEntity);
            List<Long> hotelOptionPkList = new ArrayList<>();

            List<HotelOptionInfoVo> hotelOptionInfoVoList = new ArrayList<>();
            /*List<HotelOptionInfoVo> hotelOptionInfoVoList1 = hotelOptionInfoRepository.getHotelOptionInfoList(hotelEntity.getHotelOptionInfoEntity());*/

            for(HotelOptionInfoEntity hotelOptionInfoEntity : hotelOptionInfoEntityList){
                HotelOptionInfoVo vo = HotelOptionInfoVo.builder()
                        .optionPk(hotelOptionInfoEntity.getHotelOptionEntity().getOptionPk())
                        .optionNm(hotelOptionInfoEntity.getHotelOptionEntity().getOptionNm())
                        .build();
                hotelOptionInfoVoList.add(vo);
            }
            List<HotelRoomInfoEntity> hotelRoomInfoEntityList = hotelRoomRepository.findHotelRoomInfoEntitiesByHotelEntity(hotelEntity);

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
                            .hotelRoomCost(getDiscountCost(hotelRoomInfoEntity.getHotelRoomCost(),hotelRoomInfoEntity.getDiscountPer()))
                            .hotelRoomEa(hotelRoomInfoEntity.getHotelRoomEa())
                            .roomPic(hotelRoomInfoEntity.getRoomPic())
                            .maximum(hotelRoomInfoEntity.getMaximum())
                            .discountPer(hotelRoomInfoEntity.getDiscountPer())
                            .createdAt(hotelRoomInfoEntity.getCreatedAt().toString())
                            .discountSignStatus(hotelRoomInfoEntity.getDiscountSignStatus())
                            .cancelReason(hotelRoomInfoEntity.getCancelReason())
                            .build()).collect(Collectors.toList())
                    ).build();
        }
        log.info("businessUserHotelVo : " + businessUserHotelVo);

        return businessUserHotelVo;
    }
    //사업자 유저가 등록한 호텔 방 수정
    @Transactional
    public ResVo putHotelRoomInfo(BusinessHotelRoomPutDto dto){
        Optional<UserEntity> optionalUserEntity = userRepository.findById(authenticationFacade.getLoginUserPk());
        UserEntity userEntity = optionalUserEntity.orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));

        Optional<HotelRoomInfoEntity> optionalHotelRoomInfoEntity = hotelRoomRepository.findById(dto.getHotelRoomPk());
        HotelRoomInfoEntity hotelRoomInfoEntity = optionalHotelRoomInfoEntity.orElseThrow(() -> new CustomException(HotelErrorCode.NOT_EXIST_HOTEL_ROOM));
        String hotelRoomPic = null;
        if(dto.getRoomPic() != null && !dto.getRoomPic().isEmpty()){
            String target = "/room/" + hotelRoomInfoEntity.getHotelEntity().getHotelPk() + "/" + hotelRoomInfoEntity.getHotelRoomPk();
            myFileUtils.delFiles(target);
            hotelRoomPic = myFileUtils.transferTo(dto.getRoomPic(),target);
            hotelRoomInfoEntity.setRoomPic(hotelRoomPic);
        }
        hotelRoomInfoEntity.setHotelRoomNm(dto.getHotelRoomNm());
        hotelRoomInfoEntity.setHotelRoomCost(dto.getHotelRoomCost());
        hotelRoomInfoEntity.setHotelRoomEa(dto.getHotelRoomEa());
        hotelRoomInfoEntity.setMaximum(dto.getMaximum());
        hotelRoomInfoEntity.setDogSizeEntity(dogSizeRepository.getReferenceById(dto.getSizePk()));

        return new ResVo(1);
    }

    //재웅
    // ---------------------------------------------------------------------------------------------------
    //승준
    //승준
}
