package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.CustomerBillingInfo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CustomerBillingInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerBillingInfoRepository extends JpaRepository<CustomerBillingInfo, Long> {
}
