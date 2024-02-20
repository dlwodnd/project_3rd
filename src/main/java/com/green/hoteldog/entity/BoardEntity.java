package com.green.hoteldog.entity;

import com.green.hoteldog.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_board")
public class BoardEntity extends BaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardPk;
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk",referencedColumnName = "userPk",columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userEntity;
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "board_category_pk",referencedColumnName = "boardCategoryPk",columnDefinition = "TINYINT UNSIGNED")
    private BoardCategoryEntity boardCategoryEntity;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private Long viewCount;
}