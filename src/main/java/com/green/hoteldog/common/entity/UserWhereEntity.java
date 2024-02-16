package com.green.hoteldog.common.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_user_where")
public class UserWhereEntity {
    @Id
    @OneToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_pk",referencedColumnName = "userPk",columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userEntity;

    @Column(nullable = false)
    private String addressName;

    @Column(nullable = false)
    private String region1DepthName;

    @Column(nullable = false)
    private String region2DepthName;

    @Column(nullable = false)
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
