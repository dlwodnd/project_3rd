package com.green.hoteldog.common.entity;

import com.green.hoteldog.common.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_user_dog")
public class UserDogEntity extends BaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userDogPk;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_pk",referencedColumnName = "userPk",columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userEntity;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "size_pk",referencedColumnName = "sizePk",columnDefinition = "BIGINT UNSIGNED")
    private DogSizeEntity dogSizeEntity;

    @Column(nullable = false)
    private String dogNm;

    @Column(nullable = false)
    private String dogAge;

    @Column(nullable = false)
    private String etc;

    private String pic;

}
