package com.green.hoteldog.common.entity.composite;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class HotelFavoritesComposite implements Serializable {
    private Long hotelPk;
    private Long userPk;
}
