package com.green.hoteldog.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.hoteldog.common.entity.base.CreatedAtBaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table(name = "t_business")
public class BusinessEntity extends CreatedAtBaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long businessPk;

    @JsonIgnore
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_pk", referencedColumnName = "userPk",columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userEntity;

    @Column(nullable = false)
    private String businessName;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private String bankNm;

    @Column(nullable = false)
    @ColumnDefault("'0'")
    private Long accountStatus;


    //승민
    //승민

    //승준
    //승준

    //영웅
    //영웅

    //재웅
    //재웅
}
