package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.BillingType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BillingType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillingTypeRepository extends JpaRepository<BillingType, Long> {
}
