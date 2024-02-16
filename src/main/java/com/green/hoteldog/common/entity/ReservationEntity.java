package com.green.hoteldog.common.entity;

import com.green.hoteldog.common.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_reservation")
public class ReservationEntity extends BaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resPk;
    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_pk",referencedColumnName = "userPk",columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userEntity;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_pk",referencedColumnName = "hotelPk",columnDefinition = "BIGINT UNSIGNED")
    private HotelEntity hotelEntity;

    @Column(nullable = false)
    private LocalDateTime fromDate;

    @Column(nullable = false)
    private LocalDateTime toDate;

    @Column(nullable = false)
    @ColumnDefault("'0'")
    private Long resStatus;

    private String cancelReason;
}
