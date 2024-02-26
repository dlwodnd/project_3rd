package com.green.hoteldog.manager.model;

import com.green.hoteldog.common.entity.BusinessEntity;
import lombok.Data;

public class UserListVo {
    @Data
    public static class BusinessVo {


        private Long userPk;
        private String userNum;
        private String userEmail;
        private String nickname;
        private String userAddress;
        private String phoneNum;

        public static BusinessVo UserList(BusinessEntity businessEntity) {
            BusinessVo businessDto = new BusinessVo();
            businessDto.setUserPk(businessEntity.getUserEntity().getUserPk());
            businessDto.setUserNum(businessEntity.getUserEntity().getUserNum());
            businessDto.setUserEmail(businessEntity.getUserEntity().getUserEmail());
            businessDto.setNickname(businessEntity.getUserEntity().getNickname());
            businessDto.setUserAddress(businessEntity.getUserEntity().getUserAddress());
            businessDto.setPhoneNum(businessEntity.getUserEntity().getPhoneNum());
            return businessDto;
        }
    }
}