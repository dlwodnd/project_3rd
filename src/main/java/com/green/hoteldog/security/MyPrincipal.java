package com.green.hoteldog.security;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MyPrincipal {//토큰에 값을 넣기 위해 만들어짐
    private int userPk;
    @Builder.Default
    private List<String> roles = new ArrayList();
}
