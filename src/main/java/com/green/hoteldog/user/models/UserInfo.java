package com.green.hoteldog.user.models;

import lombok.Data;

@Data
public class UserInfo {
    private int userPk;
    private String userEmail;
    private String upw;
    private String nickname;
    private String phoneNum;
    private String userAddress;
    private String userRole;
}
