package com.green.hoteldog.common.entity;

import com.green.hoteldog.common.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table(name = "t_hotel_room_info")
public class HotelRoomInfoEntity extends BaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelRoomPk;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_pk",referencedColumnName = "hotelPk",columnDefinition = "BIGINT UNSIGNED")
    private HotelEntity hotelEntity;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "size_pk",referencedColumnName = "sizePk",columnDefinition = "BIGINT UNSIGNED")
    private DogSizeEntity dogSizeEntity;

    @Column(nullable = false)
    private String hotelRoomNm;

    @Column(nullable = false)
    private String roomPic;

    @Column(nullable = false,columnDefinition = "BIGINT UNSIGNED")
    private Long hotelRoomEa;

    @Column(nullable = false,columnDefinition = "BIGINT UNSIGNED")
    private Long hotelRoomCost;

    @Column(nullable = false,columnDefinition = "BIGINT UNSIGNED")
    private Long maximum;

    @Column(nullable = false,columnDefinition = "BIGINT UNSIGNED")
    @ColumnDefault("'1'")
    private Long bookAble;

    @Column
    private String discountPer;

    @Column(nullable = false,columnDefinition = "BIGINT UNSIGNED")
    @ColumnDefault("'0'")
    private Long discountSignStatus;

    @Column
    private String cancelReason;
}
