package com.green.hoteldog.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "t_user_where")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserWhereEntity {
    @Id
    @OneToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk",referencedColumnName = "userPk",columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userEntity;

    @Column(nullable = false)
    private String addressName;

    @Column(name = "region_1depth_name",nullable = false)
    private String region1DepthName;

    @Column(name = "region_2depth_name",nullable = false)
    private String region2DepthName;

    @Column(name = "region_3depth_name",nullable = false)
    private String region3DepthName;

    @Column(nullable = false)
    private String zoneNum;

    @Column(name = "x_coordinate",nullable = false)
    private String x;

    @Column(name = "y_coordinate",nullable = false)
    private String y;

    @Column(nullable = false)
    private String detailAddress;

}