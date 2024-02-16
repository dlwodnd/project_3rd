package com.green.hoteldog.common.entity.composite;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class HotelOptionComposite implements Serializable {
    private Long hotelPk;
    private Long optionPk;
}
