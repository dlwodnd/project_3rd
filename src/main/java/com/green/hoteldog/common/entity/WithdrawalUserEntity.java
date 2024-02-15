package com.green.hoteldog.common.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_withdrawal_user")
public class WithdrawalUserEntity {
    @Id
    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_pk",referencedColumnName = "userPk",columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userPk;
    @Column(nullable = false)
    private LocalDateTime approvalDate;
    @Column(nullable = false)
    private LocalDateTime applyDate;
    @Column(nullable = false)
    private String reason;
}
