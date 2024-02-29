package com.green.hoteldog.manager.model;

import com.green.hoteldog.common.entity.BusinessEntity;
import com.green.hoteldog.common.entity.UserEntity;
import lombok.Data;

@Data

public class UserListVo2 {



        private Long userPk;
        private String userNum;
        private String userEmail;
        private String nickname;
        private String userAddress;
        private String phoneNum;
    private int maxPage;

        public static UserListVo2 UserList2(UserEntity userEntity) {
            UserListVo2 userListVo2 = new UserListVo2();
            userListVo2.setUserPk(userEntity.getUserPk());
            userListVo2.setUserNum(userEntity.getUserNum());
            userListVo2.setUserEmail(userEntity.getUserEmail());
            userListVo2.setNickname(userEntity.getNickname());
            userListVo2.setUserAddress(userEntity.getUserAddress());
            userListVo2.setPhoneNum(userEntity.getPhoneNum());
            userListVo2.setMaxPage(1);
            return userListVo2;
    }
}
