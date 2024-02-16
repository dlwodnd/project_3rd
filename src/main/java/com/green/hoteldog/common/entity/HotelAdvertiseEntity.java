package com.green.hoteldog.common.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_hotle_advertise")
public class HotelAdvertiseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelAdvertisePk;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_pk",referencedColumnName = "hotelPk",columnDefinition = "BIGINT UNSIGNED")
    private HotelEntity hotelPk;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "advertise_policy_pk",referencedColumnName = "advertisePolicyPk",columnDefinition = "BIGINT UNSIGNED")
    private HotelAdvertisePolicyEntity advertisePolicyPk;

    private LocalDateTime hotelAdvertiseToDate;
    private LocalDateTime hotelAdvertiseFromDate;
    private Long paymentStatus;
}
