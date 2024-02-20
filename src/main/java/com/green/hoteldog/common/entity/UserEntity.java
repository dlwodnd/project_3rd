package com.green.hoteldog.common.entity;

import com.green.hoteldog.common.entity.base.BaseEntity;
import com.green.hoteldog.common.entity.jpa_enum.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table(name = "t_user")
public class UserEntity extends BaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPk;
    @Column(unique = true,nullable = false)
    private String userEmail;
    @Column(length = 300,nullable = false)
    private String upw;
    @Column(unique = true,nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String phoneNum;
    @Column(nullable = false)
    private String userAddress;
    @Column(nullable = false)
    @ColumnDefault("'0'")
    private Long userStatus;
    @Column(nullable = false)
    @ColumnDefault("'USER'")
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum userRole;
//    @ToString.Exclude
//    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.PERSIST)
//    private List<BusinessEntity> businessEntities;

}
