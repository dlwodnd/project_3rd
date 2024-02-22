package com.green.hoteldog.business_user;

import com.green.hoteldog.common.entity.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<BusinessEntity, Long> {
}
