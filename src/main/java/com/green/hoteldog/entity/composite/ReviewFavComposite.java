package com.green.hoteldog.entity.composite;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class ReviewFavComposite implements Serializable {
    private Long reviewPk;
    private Long userPk;
}
