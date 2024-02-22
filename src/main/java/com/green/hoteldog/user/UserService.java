package com.green.hoteldog.user;

import com.green.hoteldog.common.AppProperties;
import com.green.hoteldog.common.Const;
import com.green.hoteldog.common.entity.UserWhereEntity;
import com.green.hoteldog.common.entity.jpa_enum.UserRoleEnum;
import com.green.hoteldog.common.utils.CookieUtils;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.common.entity.UserEntity;
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

import java.util.List;

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

    //--------------------------------------------------유저 회원가입-----------------------------------------------------
    @Transactional(rollbackFor = {Exception.class})
    public ResVo userSignup(UserSignupDto dto) {
        log.info("UserSignupDto : {}", dto);
        UserEntity userEntity = UserEntity.builder()
                .userNum("U" + RandomCodeUtils.getRandomCode(6))
                .userRole(UserRoleEnum.USER)
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
        UserSigninVo vo = new UserSigninVo();
        UserInfo userInfo = userMapper.userEntityByUserEmail(dto.getUserEmail());
        if (userInfo == null) {
            throw new CustomException(UserErrorCode.UNKNOWN_EMAIL_ADDRESS);
        }
        if (!passwordEncoder.matches(dto.getUpw(), userInfo.getUpw())) {
            throw new CustomException(UserErrorCode.MISS_MATCH_PASSWORD);
        }

        MyPrincipal myPrincipal = MyPrincipal.builder()
                .userPk(userInfo.getUserPk())
                .build();
        myPrincipal.getRoles().add(userInfo.getUserRole());
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
        vo.setUserPk(userInfo.getUserPk());
        vo.setAccessToken(at);
        vo.setNickname(userInfo.getNickname());
        return vo;
    }

    //--------------------------------------------------유저 닉네임 체크--------------------------------------------------
    public ResVo checkNickname(String nickname) {
        List<UserInfo> userInfoList = userMapper.selUserEntity();
        for (UserInfo entity : userInfoList) {
            if (entity.getNickname().equals(nickname)) {
                throw new CustomException(UserErrorCode.ALREADY_USED_NICKNAME);
            }
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
        dto.setUserPk(facade.getLoginUserPk());
        if (dto.getUserPk() == 0) {
            throw new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED);
        }
        UserInfo entity = userMapper.userEntityByUserPk(dto.getUserPk());
        if (!passwordEncoder.matches(dto.getUpw(), entity.getUpw())) {
            throw new CustomException(UserErrorCode.MISS_MATCH_PASSWORD);
        }
        UserInfoVo vo = new UserInfoVo();
        vo.setUserPk(entity.getUserPk());
        vo.setUserEmail(entity.getUserEmail());
        vo.setNickname(entity.getNickname());
        vo.setPhoneNum(entity.getPhoneNum());
        vo.setUserAddress(entity.getUserAddress());
        vo.setAddressEntity(userMapper.getUserAddress(entity.getUserPk()));
        return vo;
    }

    //--------------------------------------------------유저 정보 업데이트-------------------------------------------------
    public ResVo updUserInfo(UserUpdateDto dto) {
        dto.setUserPk(facade.getLoginUserPk());
        log.info("UserUpdateDto : {}", dto);
        if (dto.getUserPk() == 0) {
            throw new CustomException(AuthorizedErrorCode.NOT_AUTHORIZED);
        }
        //dto.setUserAddress(dto.getAddressEntity().getAddressName() + " " + dto.getAddressEntity().getDetailAddress());
        userMapper.updateUserInfo(dto);
        dto.getAddressEntity().setUserPk(dto.getUserPk());
        userMapper.updateUserAddress(dto.getAddressEntity());
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
        vo.setUserPk(facade.getLoginUserPk());
        vo.setAccessToken(at);
        return vo;
    }
}
