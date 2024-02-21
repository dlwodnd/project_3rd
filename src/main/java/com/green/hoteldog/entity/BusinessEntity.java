package com.green.hoteldog.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_buisiness")
public class BusinessEntity  {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long businessPk;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk", referencedColumnName = "userPk",columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userEntity;

    @Column(nullable = false)
    private String accountNumber;
    @Column(nullable = false)
    private String bankNm;
    @Column(nullable = false)
    private Long accountStatus;
}
