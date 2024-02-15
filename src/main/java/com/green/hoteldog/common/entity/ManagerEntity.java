package com.green.hoteldog.common.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_manager")
public class ManagerEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long managerPk;
    @Column(nullable = false,unique = true)
    private String id;
    @Column(nullable = false)
    private String pw;
}
