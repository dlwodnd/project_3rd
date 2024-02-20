package com.green.hoteldog.entity;

import com.green.hoteldog.entity.base.CreatedAtBaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_payment_ad")
public class PaymentAdEntity extends CreatedAtBaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentPk;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "business_pk",referencedColumnName = "businessPk",columnDefinition = "BIGINT UNSIGNED")
    private BusinessEntity businessEntity;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_advertise_pk",referencedColumnName = "hotelAdvertisePk",columnDefinition = "BIGINT UNSIGNED")
    private HotelAdvertiseEntity hotelAdvertiseEntity;

    private Long paymentStatus;

}
