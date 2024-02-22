package com.green.hoteldog.common.entity;

import com.green.hoteldog.common.entity.base.CreatedAtBaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_payment_ad")
public class PaymentAdEntity extends CreatedAtBaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentPk;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "business_pk",referencedColumnName = "businessPk",columnDefinition = "BIGINT UNSIGNED")
    private BusinessEntity businessEntity;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_advertise_pk",referencedColumnName = "hotelAdvertisePk",columnDefinition = "BIGINT UNSIGNED")
    private HotelAdvertiseEntity hotelAdvertiseEntity;


    @ColumnDefault("'0'")
    private Long paymentStatus;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime paymentDate;

    @Column(nullable = false)
    private String paymentAdNum;

    //승민
    //승민

    //승준
    //승준

    //영웅
    //영웅

    //재웅
    //재웅

}
