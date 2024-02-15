package com.green.hoteldog.common.entity;

import com.green.hoteldog.common.entity.composite.ReviewFavComposite;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_review_fav")
public class ReviewFavEntity {
    @EmbeddedId
    private ReviewFavComposite composite;

    @ManyToOne(optional = false,cascade = CascadeType.REMOVE)
    @MapsId("reviewPk")
    @JoinColumn(name = "review_pk",columnDefinition = "BIGINT UNSIGNED")
    private ReviewEntity reviewPk;

    @ManyToOne(optional = false,cascade = CascadeType.REMOVE)
    @MapsId("userPk")
    @JoinColumn(name = "user_pk",columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userPk;
    
}
