package com.green.hoteldog.common.entity;

import com.green.hoteldog.common.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_review_pic")
public class ReviewPicEntity extends BaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewPicPk;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "review_pk",referencedColumnName = "reviewPk",columnDefinition = "BIGINT UNSIGNED")
    private ReviewEntity reviewEntity;

    @Column(nullable = false)
    private String reviewPic;
}
