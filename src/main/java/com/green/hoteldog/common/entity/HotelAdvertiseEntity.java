package com.green.hoteldog.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_hotel_advertise")
public class HotelAdvertiseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelAdvertisePk;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_pk",referencedColumnName = "hotelPk",columnDefinition = "BIGINT UNSIGNED")
    private HotelEntity hotelEntity;

    @Column(nullable = false)
    private LocalDateTime hotelAdvertiseToDate;

    @Column(nullable = false)
    private LocalDateTime hotelAdvertiseFromDate;

    @Column(nullable = false)
    @ColumnDefault("'0'")
    private Long paymentStatus;

    @Column(nullable = false)
    @ColumnDefault("'0'")
    private Long signStatus;

    @Column(nullable = false)
    private String cancelReason;

    @Column(nullable = false)
    private String hotelAdvertiseNum;
}
