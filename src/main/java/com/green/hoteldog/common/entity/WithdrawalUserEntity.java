package com.green.hoteldog.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_withdrawal_user")
public class WithdrawalUserEntity {
    @Id
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk",referencedColumnName = "userPk",columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userPk;

    @Column(nullable = false)
    private LocalDateTime approvalDate;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime applyDate;

    @Column(nullable = false, columnDefinition = "VARCHAR(1000)")
    private String reason;
    //
}
