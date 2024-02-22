package com.green.hoteldog.common.entity;

import com.green.hoteldog.common.entity.base.CreatedAtBaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_hotel_suspended")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
