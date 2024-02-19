package com.green.hoteldog.common.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_board_comment")
public class BoardCommentEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentPk;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk",referencedColumnName = "userPk",columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userEntity;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "board_pk",referencedColumnName = "boardPk",columnDefinition = "BIGINT UNSIGNED")
    private BoardEntity boardEntity;

    @Column(nullable = false)
    private String comment;
}
