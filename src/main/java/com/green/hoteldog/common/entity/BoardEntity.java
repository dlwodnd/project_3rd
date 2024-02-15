package com.green.hoteldog.common.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_board")
public class BoardEntity extends BaseEntity{
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardPk;
    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_pk",referencedColumnName = "userPk",columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userPk;
    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "board_category_pk",referencedColumnName = "boardCategoryPk",columnDefinition = "BIGINT UNSIGNED")
    private BoardCategoryEntity boardCategoryPk;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private Long viewCount;
}
