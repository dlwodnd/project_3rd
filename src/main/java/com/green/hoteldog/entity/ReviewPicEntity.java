package com.green.hoteldog.entity;

import com.green.hoteldog.entity.base.CreatedAtBaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_review_pic")
public class ReviewPicEntity extends CreatedAtBaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewPicPk;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "review_pk",referencedColumnName = "reviewPk",columnDefinition = "BIGINT UNSIGNED")
    private ReviewEntity reviewEntity;

    @Column(nullable = false)
    private String pic;
}
