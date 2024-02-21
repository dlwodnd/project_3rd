package com.green.hoteldog.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_res_payment")
@Data
public class ResPaymentEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resPaymentPk;


    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "res_pk",referencedColumnName = "resPk",columnDefinition = "BIGINT UNSIGNED")
    private ReservationEntity reservationEntity;


    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk",referencedColumnName = "userPk",columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userEntity;


    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "business_pk",referencedColumnName = "businessPk",columnDefinition = "BIGINT UNSIGNED")
    private BusinessEntity businessEntity;

    @Column(nullable = false)
    private Long paymentStatus;

    @Column(nullable = false)
    private String paymentAmount;

    @Column(nullable = false)
    private String resPaymentNum;
}
