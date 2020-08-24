package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.BillingPeriod;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BillingPeriod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillingPeriodRepository extends JpaRepository<BillingPeriod, Long> {
}
