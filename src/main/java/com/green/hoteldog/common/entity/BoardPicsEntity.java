package com.green.hoteldog.common.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_board_pic")
public class BoardPicsEntity extends BaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardPicPk;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "board_pk",referencedColumnName = "boardPk",columnDefinition = "BIGINT UNSIGNED")
    private BoardEntity boardPk;
    @Column(nullable = false)
    private String pic;
}
