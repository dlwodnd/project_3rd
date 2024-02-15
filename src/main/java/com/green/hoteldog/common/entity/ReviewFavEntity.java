package com.green.hoteldog.common.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "t_review_fav")
public class ReviewFavEntity {
    @EmbeddedId
    
}
