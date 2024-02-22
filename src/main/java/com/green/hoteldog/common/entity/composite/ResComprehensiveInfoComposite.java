package com.green.hoteldog.common.entity.composite;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Data
@Embeddable
public class ResComprehensiveInfoComposite implements Serializable {
    private Long resPk;
    private Long hotelRoomPk;
    private Long resDogPk;
}
