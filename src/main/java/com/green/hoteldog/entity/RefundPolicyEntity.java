package com.green.hoteldog.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_refund_policy")
@Data
public class RefundPolicyEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyPk;

    @Column(nullable = false)
    private Long daysBeforeCheckIn;

    @Column(nullable = false)
    private Long refundPercentage;
}
