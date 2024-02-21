package com.green.hoteldog.entity;

import com.green.hoteldog.entity.base.CreatedAtBaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_hotel_suspended")
@Data
public class HotelSuspendedEntity extends CreatedAtBaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long suspendedPk;

    @OneToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_pk",referencedColumnName = "hotelPk",columnDefinition = "BIGINT UNSIGNED")
    private HotelEntity hotelEntity;

    @Column(nullable = false)
    private String suspendedReason;
}
