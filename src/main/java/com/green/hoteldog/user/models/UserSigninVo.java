package com.green.hoteldog.user.models;

import lombok.Data;

@Data
public class UserSigninVo {
    private long userPk;
    private String userRole;
    private String nickname;
    private String accessToken;
    /*
    private String depthName; //유저 주소
    private List<Integer> sizePkList; //유저가 보유한 강아지 사이즈
    */
}
