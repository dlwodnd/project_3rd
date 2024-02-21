package com.green.hoteldog.entity;

import com.green.hoteldog.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_hotel")
public class HotelEntity extends BaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelPk;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
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

    @Column(nullable = false)
    private Long advertise;

    @Column(nullable = false)
    private Long approval;

    @Column(nullable = false)
    private Long signStatus;

    private String cancelReason;

}
