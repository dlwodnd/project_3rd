package com.green.hoteldog.user;

import com.green.hoteldog.user.models.BusinessUserSignupDto;
import com.green.hoteldog.business_user.model.HotelInsDto;
import com.green.hoteldog.common.AppProperties;
import com.green.hoteldog.common.Const;
import com.green.hoteldog.common.entity.*;
import com.green.hoteldog.common.entity.composite.HotelOptionComposite;
import com.green.hoteldog.common.entity.jpa_enum.UserRoleEnum;
import com.green.hoteldog.common.repository.*;
import com.green.hoteldog.common.utils.CookieUtils;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.common.utils.MyFileUtils;
import com.green.hoteldog.common.utils.RandomCodeUtils;
import com.green.hoteldog.exceptions.AuthorizedErrorCode;
import com.green.hoteldog.exceptions.CustomException;
import com.green.hoteldog.exceptions.UserErrorCode;
import com.green.hoteldog.security.AuthenticationFacade;
import com.green.hoteldog.security.JwtTokenProvider;
import com.green.hoteldog.security.MyUserDetails;
import com.green.hoteldog.security.MyPrincipal;
import com.green.hoteldog.user.models.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AppProperties appProperties;
    private final CookieUtils cookie;
    private final AuthenticationFacade facade;
    private final UserRepository userRepository;
    private final BusinessRepository businessRepository;
    private final WithdrawalUserRepository withdrawalUserRepository;
    private final MyFileUtils myFileUtils;
    private final HotelOptionRepository hotelOptionRepository;
    private final HotelOptionInfoRepository hotelOptionInfoRepository;
    private final DogSizeRepository dogSizeRepository;
    private final HotelRepository hotelRepository;
    private final HotelRoomRepository hotelRoomRepository;

    //--------------------------------------------------유저 회원가입-----------------------------------------------------
    @Transactional(rollbackFor = {Exception.class})
    public ResVo userSignup(UserSignupDto dto) {
        log.info("UserSignupDto : {}", dto);
        UserEntity userEntity = UserEntity.builder()
                .userStatus(0L)
                .userNum("U" + RandomCodeUtils.getRandomCode(6))
                .userEmail(dto.getEmailResponseVo().getEmail())
                .upw(passwordEncoder.encode(dto.getUpw()))
                .nickname(dto.getNickname())
                .phoneNum(dto.getPhoneNum())
                .userAddress(dto.getAddressEntity().getAddressName() + " " + dto.getAddressEntity().getDetailAddress())
                .build();
        userRepository.save(userEntity);

        UserWhereEntity userWhereEntity = UserWhereEntity.builder()
                .userEntity(userEntity)
                .x(dto.getAddressEntity().getX())
                .y(dto.getAddressEntity().getY())
                .addressName(dto.getAddressEntity().getAddressName())
                .userEntity(userEntity)
                .zoneNum(dto.getAddressEntity().getZoneNum())
                .region1DepthName(dto.getAddressEntity().getRegion1DepthName())
                .region2DepthName(dto.getAddressEntity().getRegion2DepthName())
                .region3DepthName(dto.getAddressEntity().getRegion3DepthName())
                .detailAddress(dto.getAddressEntity().getDetailAddress())
                .build();
        userEntity.setUserWhereEntity(userWhereEntity);
        return new ResVo(1);
    }

    //--------------------------------------------------유저 로그인-------------------------------------------------------
    public UserSigninVo userSignin(HttpServletResponse response, HttpServletRequest request, UserSigninDto dto) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUserEmail(dto.getUserEmail());

        UserEntity userEntity = userEntityOptional.orElseThrow(() -> new CustomException(UserErrorCode.UNKNOWN_EMAIL_ADDRESS));

        if (!passwordEncoder.matches(dto.getUpw(), userEntity.getUpw())) {
            throw new CustomException(UserErrorCode.MISS_MATCH_PASSWORD);
        }
        Optional<WithdrawalUserEntity> withdrawalUserEntityOptional = withdrawalUserRepository.findById(userEntity.getUserPk());
        if (withdrawalUserEntityOptional.isPresent()) {
            throw new CustomException(UserErrorCode.WITHDRAWAL_USER);
        }

        MyPrincipal myPrincipal = MyPrincipal.builder()
                .userPk(userEntity.getUserPk())
                .build();
        myPrincipal.getRoles().add(userEntity.getUserRole().name());
        String at = tokenProvider.generateAccessToken(myPrincipal);
        //엑서스 토큰 값 받아오기
        String rt = tokenProvider.generateRefreshToken(myPrincipal);
        //리프레쉬 토큰 값 받아오기
        /*List<Integer> dogSizeList = mapper.selUserDogSize(userInfo.getUserPk());*/

        int rtCookieMaxAge = (int) appProperties.getJwt().getRefreshTokenExpiry() / 1000;
        cookie.deleteCookie(response, "rt");
        cookie.setCookie(response, "rt", rt, rtCookieMaxAge);

        /*vo.setDepthName(mapper.selUserDepthName(userInfo.getUserPk()));
        vo.setSizePkList(dogSizeList);*/
        UserSigninVo vo = new UserSigninVo();
        vo.setUserRole(userEntity.getUserRole().name());
        vo.setUserPk(userEntity.getUserPk());
        vo.setAccessToken(at);
        vo.setNickname(userEntity.getNickname());
        return vo;
    }

    //--------------------------------------------------유저 닉네임 체크--------------------------------------------------
    public ResVo checkNickname(String nickname) {
        UserEntity userEntity = userRepository.findByNickname(nickname);
        if (userEntity != null) {
            throw new CustomException(UserErrorCode.ALREADY_USED_NICKNAME);
        }

        return new ResVo(1);
    }

    //--------------------------------------------------유저 로그아웃-----------------------------------------------------
    public ResVo signout(HttpServletResponse response) {
        cookie.deleteCookie(response, "rt");
        return new ResVo(1);
    }

    //--------------------------------------------------유저 정보 조회----------------------------------------------------
    public UserInfoVo getUserInfo(UserInfoDto dto) {
        Optional<UserEntity> optionalUser = userRepository.findById(facade.getLoginUserPk());
        UserEntity userEntity = optionalUser.orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));

        if (!passwordEncoder.matches(dto.getUpw(), userEntity.getUpw())) {
            throw new CustomException(UserErrorCode.MISS_MATCH_PASSWORD);
        }
        UserInfoVo vo = new UserInfoVo();

        vo.setUserPk(userEntity.getUserPk());
        vo.setUserEmail(userEntity.getUserEmail());
        vo.setNickname(userEntity.getNickname());
        vo.setPhoneNum(userEntity.getPhoneNum());
        vo.setUserAddress(userEntity.getUserAddress());
        vo.setAddressEntity(UserAddressInfo.builder()
                .userPk(userEntity.getUserPk())
                .x(userEntity.getUserWhereEntity().getX())
                .y(userEntity.getUserWhereEntity().getY())
                .addressName(userEntity.getUserWhereEntity().getAddressName())
                .detailAddress(userEntity.getUserWhereEntity().getDetailAddress())
                .zoneNum(userEntity.getUserWhereEntity().getZoneNum())
                .region1DepthName(userEntity.getUserWhereEntity().getRegion1DepthName())
                .region2DepthName(userEntity.getUserWhereEntity().getRegion2DepthName())
                .region3DepthName(userEntity.getUserWhereEntity().getRegion3DepthName())
                .build());
        return vo;
    }

    //--------------------------------------------------유저 정보 업데이트-------------------------------------------------
    @Transactional
    public ResVo updUserInfo(UserUpdateDto dto) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(facade.getLoginUserPk());
        UserEntity userEntity = optionalUserEntity.orElseThrow(() -> new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED));
        userEntity.setUserAddress(dto.getAddressEntity().getAddressName() + " " + dto.getAddressEntity().getDetailAddress());
        userEntity.setNickname(dto.getNickname());
        UserWhereEntity userWhereEntity = userEntity.getUserWhereEntity();
        userWhereEntity.setX(dto.getAddressEntity().getX());
        userWhereEntity.setY(dto.getAddressEntity().getY());
        userWhereEntity.setAddressName(dto.getAddressEntity().getAddressName());
        userWhereEntity.setZoneNum(dto.getAddressEntity().getZoneNum());
        userWhereEntity.setRegion1DepthName(dto.getAddressEntity().getRegion1DepthName());
        userWhereEntity.setRegion2DepthName(dto.getAddressEntity().getRegion2DepthName());
        userWhereEntity.setRegion3DepthName(dto.getAddressEntity().getRegion3DepthName());
        userWhereEntity.setDetailAddress(dto.getAddressEntity().getDetailAddress());
        userEntity.setUserWhereEntity(userWhereEntity);
        userEntity.setPhoneNum(dto.getPhoneNum());
        return new ResVo(Const.SUCCESS);
    }

    //-------------------------------------------------리프레쉬 토큰 재발급------------------------------------------------
    public RefreshTokenVo getRefreshToken(HttpServletRequest request) {
        RefreshTokenVo vo = new RefreshTokenVo();
        Cookie userCookie = cookie.getCookie(request, "rt");
        if (userCookie == null) {
            throw new CustomException(AuthorizedErrorCode.NOT_EXISTS_REFRESH_TOKEN);
        }
        String token = userCookie.getValue();
        if (!tokenProvider.isValidateToken(token)) {
            throw new CustomException(AuthorizedErrorCode.REFRESH_TOKEN_IS_EXPIRATION);
        }
        MyUserDetails myUserDetails = (MyUserDetails) tokenProvider.getUserDetailsFromToken(token);
        MyPrincipal myprincipal = myUserDetails.getMyPrincipal();
        String at = tokenProvider.generateAccessToken(myprincipal);
        vo.setUserPk((int)facade.getLoginUserPk());
        vo.setAccessToken(at);
        return vo;
    }
    // 사업자 유저 로그인
    public BusinessSigninVo businessSignin(HttpServletResponse response, HttpServletRequest request, UserSigninDto dto) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUserEmail(dto.getUserEmail());

        UserEntity userEntity = userEntityOptional.orElseThrow(() -> new CustomException(UserErrorCode.UNKNOWN_EMAIL_ADDRESS));

        Optional<BusinessEntity> businessEntityOptional = businessRepository.findByUserEntity(userEntity);

        BusinessEntity businessEntity = businessEntityOptional.orElseThrow(() -> new CustomException(UserErrorCode.NOT_BUSINESS_USER));


        if (!passwordEncoder.matches(dto.getUpw(), userEntity.getUpw())) {
            throw new CustomException(UserErrorCode.MISS_MATCH_PASSWORD);
        }
        Optional<WithdrawalUserEntity> withdrawalUserEntityOptional = withdrawalUserRepository.findById(userEntity.getUserPk());
        if (withdrawalUserEntityOptional.isPresent()) {
            throw new CustomException(UserErrorCode.WITHDRAWAL_USER);
        }

        MyPrincipal myPrincipal = MyPrincipal.builder()
                .userPk(userEntity.getUserPk())
                .build();
        myPrincipal.getRoles().add(userEntity.getUserRole().name());
        String at = tokenProvider.generateAccessToken(myPrincipal);
        //엑서스 토큰 값 받아오기
        String rt = tokenProvider.generateRefreshToken(myPrincipal);
        //리프레쉬 토큰 값 받아오기
        /*List<Integer> dogSizeList = mapper.selUserDogSize(userInfo.getUserPk());*/

        int rtCookieMaxAge = (int) appProperties.getJwt().getRefreshTokenExpiry() / 1000;
        cookie.deleteCookie(response, "rt");
        cookie.setCookie(response, "rt", rt, rtCookieMaxAge);

        return BusinessSigninVo.builder()
                .businessPk(businessEntity.getBusinessPk())
                .userPk(userEntity.getUserPk())
                .accessToken(at)
                .nickname(userEntity.getNickname())
                .bankNm(businessEntity.getBankNm())
                .accountNumber(businessEntity.getAccountNumber())
                .userRole(userEntity.getUserRole().name())
                .build();
    }
    //사업자 유저 회원가입
    @Transactional
    public ResVo insBusinessUser(BusinessUserSignupDto businessUserDto, HotelInsDto hotelDto){

        //유저 엔티티 등록
        UserEntity userEntity = UserEntity.builder()
                .userStatus(0L)
                .userRole(UserRoleEnum.BUSINESS_USER)
                .userNum("U" + RandomCodeUtils.getRandomCode(6))
                .userEmail(businessUserDto.getEmailResponseVo().getEmail())
                .upw(passwordEncoder.encode(businessUserDto.getUpw()))
                .nickname(businessUserDto.getNickname())
                .phoneNum(businessUserDto.getPhoneNum())
                .userAddress(businessUserDto.getAddressEntity().getAddressName() + " " + businessUserDto.getAddressEntity().getDetailAddress())
                .build();
        userRepository.save(userEntity);

        UserWhereEntity userWhereEntity = UserWhereEntity.builder()
                .userEntity(userEntity)
                .x(businessUserDto.getAddressEntity().getX())
                .y(businessUserDto.getAddressEntity().getY())
                .addressName(businessUserDto.getAddressEntity().getAddressName())
                .userEntity(userEntity)
                .zoneNum(businessUserDto.getAddressEntity().getZoneNum())
                .region1DepthName(businessUserDto.getAddressEntity().getRegion1DepthName())
                .region2DepthName(businessUserDto.getAddressEntity().getRegion2DepthName())
                .region3DepthName(businessUserDto.getAddressEntity().getRegion3DepthName())
                .detailAddress(businessUserDto.getAddressEntity().getDetailAddress())
                .build();
        userEntity.setUserWhereEntity(userWhereEntity);
        //유저 엔티티 등록

        //사업자 엔티티 등록
        BusinessEntity businessEntity = new BusinessEntity();
        businessEntity.setUserEntity(userEntity);
        businessEntity.setAccountNumber("B" + RandomCodeUtils.getRandomCode(6));
        businessEntity.setAccountNumber("0000");
        businessEntity.setBankNm("0000");
        businessEntity.setAccountStatus(0L);
        businessEntity.setBusinessName(businessUserDto.getBusinessName());
        businessRepository.save(businessEntity);
        //사업자 엔티티 등록

        //호텔 등록
        HotelEntity hotelEntity = HotelEntity.builder()
                .hotelNm(hotelDto.getHotelNm())
                .businessEntity(businessEntity)
                .hotelDetailInfo(hotelDto.getHotelDetailInfo())
                .businessNum(hotelDto.getBusinessNum())
                .hotelCall(hotelDto.getHotelCall())
                .businessCertificate("x")
                .advertise(0L)
                .approval(0L)
                .signStatus(0L)
                .hotelNum("H" + RandomCodeUtils.getRandomCode(6))
                .build();
        String target = "/manager/hotel/" + businessEntity.getBusinessPk();
        String hotelCertificationFile = myFileUtils.transferTo(hotelDto.getBusinessCertificationFile(),target);
        hotelEntity.setBusinessCertificate(hotelCertificationFile);
        hotelRepository.save(hotelEntity);


        HotelWhereEntity hotelWhereEntity = HotelWhereEntity.builder()
                .x(hotelDto.getHotelAddressInfo().getX())
                .y(hotelDto.getHotelAddressInfo().getY())
                .addressName(hotelDto.getHotelAddressInfo().getAddressName())
                .zoneNum(hotelDto.getHotelAddressInfo().getZoneNum())
                .detailAddress(hotelDto.getHotelAddressInfo().getDetailAddress())
                .region1DepthName(hotelDto.getHotelAddressInfo().getRegion1DepthName())
                .region2DepthName(hotelDto.getHotelAddressInfo().getRegion2DepthName())
                .region3DepthName(hotelDto.getHotelAddressInfo().getRegion3DepthName())
                .hotelEntity(hotelEntity)
                .build();
        hotelEntity.setHotelWhereEntity(hotelWhereEntity);

        List<HotelPicEntity> hotelPicEntityList = new ArrayList<>();
        for(MultipartFile file : hotelDto.getHotelPics()){
            target = "/hotel/" + hotelEntity.getHotelPk();
            String hotelPicFile = myFileUtils.transferTo(file,target);
            HotelPicEntity hotelPicsEntity = HotelPicEntity.builder()
                    .hotelEntity(hotelEntity)
                    .pic(hotelPicFile)
                    .build();
            hotelPicEntityList.add(hotelPicsEntity);
        }
        hotelEntity.setHotelPicEntity(hotelPicEntityList);

        List<HotelOptionEntity> hotelOptionEntityList = hotelOptionRepository.findAllById(hotelDto.getHotelOption());

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
        //호텔 등록


        //호텔 방 자동 등록
        List<DogSizeEntity> dogSizeEntityList = dogSizeRepository.findAll();
        List<HotelRoomInfoEntity> hotelRoomInfoEntityList = new ArrayList<>();
        HotelRoomInfoEntity smallRoomInfo = HotelRoomInfoEntity.builder()
                .dogSizeEntity(dogSizeRepository.findById(1L).get())
                .hotelEntity(hotelEntity)
                .hotelRoomNm("소형견(7kg 이하)")
                .build();
        hotelRoomInfoEntityList.add(smallRoomInfo);
        HotelRoomInfoEntity mediumRoomInfo = HotelRoomInfoEntity.builder()
                .dogSizeEntity(dogSizeRepository.findById(2L).get())
                .hotelEntity(hotelEntity)
                .hotelRoomNm("중형견(15kg 이하)")
                .build();
        hotelRoomInfoEntityList.add(mediumRoomInfo);
        HotelRoomInfoEntity largeRoomInfo = HotelRoomInfoEntity.builder()
                .dogSizeEntity(dogSizeRepository.findById(3L).get())
                .hotelEntity(hotelEntity)
                .hotelRoomNm("대형견(40kg 이하)")
                .build();
        hotelRoomInfoEntityList.add(largeRoomInfo);
        HotelRoomInfoEntity superLargeRoomInfo = HotelRoomInfoEntity.builder()
                .dogSizeEntity(dogSizeRepository.findById(4L).get())
                .hotelEntity(hotelEntity)
                .hotelRoomNm("초대형견(40kg 초과)")
                .build();
        hotelRoomInfoEntityList.add(superLargeRoomInfo);
        HotelRoomInfoEntity groupRoomInfo = HotelRoomInfoEntity.builder()
                .dogSizeEntity(dogSizeRepository.findById(4L).get())
                .hotelEntity(hotelEntity)
                .hotelRoomNm("단체방")
                .build();
        hotelRoomInfoEntityList.add(groupRoomInfo);
        hotelRoomRepository.saveAll(hotelRoomInfoEntityList);
        //호텔 방 자동 등록


        return new ResVo(1);
    }

}
