package com.green.hoteldog.user.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessSigninVo {
    private long userPk;
    private long businessPk;
    private String userRole;
    private String nickname;
    private String accessToken;

    private String accountNumber;
    private String bankNm;
}
