package com.green.hoteldog.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_dog_size")
public class DogSizeEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sizePk;

    @Column(nullable = false)
    private String dogSize;

    @Column(nullable = false)
    private Long dogCost;
}
