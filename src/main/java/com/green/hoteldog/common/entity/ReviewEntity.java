package com.green.hoteldog.common.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_review")
public class ReviewEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewPk;
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "res_pk",referencedColumnName = "resPk",columnDefinition = "BIGINT UNSIGNED")
    private ReservationEntity reservationEntity;
    @Column(nullable = false)
    private String comment;
    @Column(nullable = false)
    private Long score;
}
