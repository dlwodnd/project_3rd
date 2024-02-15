package com.green.hoteldog.user;

import com.green.hoteldog.user.models.UserAddressInfo;
import com.green.hoteldog.user.models.UserInfo;
import com.green.hoteldog.user.models.UserSignupDto;
import com.green.hoteldog.user.models.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UserRepository implements UserRepositoryRef{
    private final UserMapper userMapper;
    public Integer userSignup(UserSignupDto dto) {
        return userMapper.userSignup(dto);
    }
    public Integer insUserAddress(UserAddressInfo entity) {
        return userMapper.insUserAddress(entity);
    }
    public UserInfo userEntityByUserEmail(String userEmail) {
        return userMapper.userEntityByUserEmail(userEmail);
    }
    public List<UserInfo> selUserEntity() {
        return userMapper.selUserEntity();
    }
    public List<Integer> selUserDogSize(int userPk) {
        return userMapper.selUserDogSize(userPk);
    }
    public String selUserDepthName(int userPk) {
        return userMapper.selUserDepthName(userPk);
    }
    public UserInfo userEntityByUserPk(int userPk) {
        return userMapper.userEntityByUserPk(userPk);
    }
    public Integer updateUserInfo(UserUpdateDto dto) {
        return userMapper.updateUserInfo(dto);
    }
}
