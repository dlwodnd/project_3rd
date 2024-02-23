package com.green.hoteldog.common.entity;

import com.green.hoteldog.common.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Data
@Entity
@Table(name = "t_hotel")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelEntity extends BaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelPk;

    @OneToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "business_pk",referencedColumnName = "businessPk",columnDefinition = "BIGINT UNSIGNED")
    private BusinessEntity businessEntity;

    @Column(nullable = false)
    private String hotelNm;

    @Column(nullable = false)
    private String hotelDetailInfo;

    @Column(nullable = false,unique = true)
    private String businessNum;

    @Column(nullable = false)
    private String hotelCall;


    @ColumnDefault("'0'")
    private Long advertise;


    @ColumnDefault("'0'")
    private Long approval;


    @ColumnDefault("'0'")
    private Long signStatus;

    private String cancelReason;

    @Column(nullable = false)
    private String hotelNum;

    private String refuseReason;

    @Column(nullable = false)
    private String businessCertificate;

    //승민
    //승민

    //승준
    //승준

    //영웅
    //영웅

    //재웅
    @ToString.Exclude
    @OneToMany(mappedBy = "hotelEntity",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private List<HotelPicEntity> hotelPicEntity;

    @ToString.Exclude
    @OneToOne(mappedBy = "hotelEntity",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private HotelWhereEntity hotelWhereEntity;
    //재웅
}
