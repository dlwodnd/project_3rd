package com.green.hoteldog.business_user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HotelAdvertiseApplicationDto {
    @JsonIgnore
    private int iuser;
    private int hotelPk;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int cost; // 가격
}
