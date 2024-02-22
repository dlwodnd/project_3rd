package com.green.hoteldog.business_user;

import com.green.hoteldog.business_user.model.*;
import com.green.hoteldog.common.Const;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.common.entity.HotelEntity;
import com.green.hoteldog.common.entity.HotelRoomInfoEntity;
import com.green.hoteldog.common.entity.HotelSuspendedEntity;
import com.green.hoteldog.hotel.repository.HotelRepository;
import com.green.hoteldog.hotel.repository.HotelRoomRepository;
import com.green.hoteldog.hotel.repository.HotelSuspendedRepository;
import com.green.hoteldog.security.AuthenticationFacade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessService {
    private final AuthenticationFacade authenticationFacade;
    private final BusinessRepository repository;
    private final HotelRepository hotelRepository;
    private final HotelSuspendedRepository suspendedRepository;
    private final HotelRoomRepository hotelRoomRepository;

    // 호텔 상태 전환 (1 - 운영 상태, 2 - 운영 중지 상태)
    @Transactional
    public ResVo insHotelStateChange(HotelSateChangeInsDto dto){
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hotel");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();*/

        long loginIuser = (long) authenticationFacade.getLoginUserPk();
        // 1. 운영 중지 신청
        // 운영 중지 상태를 만들어 예약을 못하게 합니다.
        // 호텔 테이블 상태, 호텔 방 테이블 상태 변경
        if(dto.getStateChange() == 1){

            HotelEntity hotelEntity = hotelRepository.findByHotelPk(loginIuser);

            HotelSuspendedEntity suspendedEntity = HotelSuspendedEntity.builder()
                    .hotelEntity(hotelEntity)
                    .suspendedReason(dto.getSuspendReason())
                    .build();

            HotelSuspendedEntity savedEntity = suspendedRepository.save(suspendedEntity);
            List<Long> hotelPkList = new ArrayList<>();
            hotelPkList.add(hotelEntity.getHotelPk());

            List<HotelRoomInfoEntity> roomInfoEntity = hotelRoomRepository.findAllById(hotelPkList);

            List<HotelRoomInfoEntity> UpdRoomInfoEntity = roomInfoEntity.stream().map(room ->
                    HotelRoomInfoEntity.builder()
                            .roomAble((long) 2)
                            .build()).collect(Collectors.toList());
            for ( HotelRoomInfoEntity updRoominfo : UpdRoomInfoEntity ) {

            }

            if(savedEntity.getSuspendedPk() == 0){
                return new ResVo(Const.FAIL); // 나중에 예외처리 하기
            }
            return new ResVo(Const.SUCCESS);
        }else{


            return new ResVo(Const.SUCCESS);
        }



        // 2. 운영 중지 철회 - 호텔 상테 1로 변경


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
    public ResVo insHotelInfo(){
        return null;
    }
    //사업자 유저가 등록한 호텔 리스트
    public List<BusinessUserHotelVo> getBusinessUserHotel(){
        return null;
    }
    //사업자 유저가 등록한 호텔 방 수정
    public ResVo putHotelRoomInfo(){
        return null;
    }
    //사업자 유저가 등록한 방 리스트
    public List<BusinessUserHotelRoomVo> getBusinessUserHotelRoomList(){
        return null;
    }
    //재웅
}
