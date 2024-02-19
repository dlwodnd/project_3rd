package com.green.hoteldog.common.entity;

import com.green.hoteldog.common.entity.base.CreatedAtBaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_res_dog_info")
public class ResDogInfoEntity extends CreatedAtBaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resDogPk;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "size_pk",referencedColumnName = "sizePk",columnDefinition = "BIGINT UNSIGNED")
    private DogSizeEntity dogSizeEntity;

    @Column(nullable = false)
    private String dogNm;

    @Column(nullable = false)
    private String dogAge;

    @Column(nullable = false)
    private String information;
}
