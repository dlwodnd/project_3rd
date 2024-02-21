package com.green.hoteldog.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Table(name = "t_refund")
@Entity
@Data
public class RefundEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refundPk;


    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_pk",referencedColumnName = "policyPk",columnDefinition = "BIGINT UNSIGNED")
    private RefundPolicyEntity refundPolicyEntity;


    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk",referencedColumnName = "userPk",columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userEntity;


    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "res_pk",referencedColumnName = "resPk",columnDefinition = "BIGINT UNSIGNED")
    private ReservationEntity reservationEntity;

    @Column(nullable = false)
    private String refundNum;

    @Column(nullable = false)
    @ColumnDefault("'0'")
    private Long refundStatus;


    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime refundDate;

    @Column(nullable = false)
    private String refundAmount;

}