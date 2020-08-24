package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.AccountTaxInfo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AccountTaxInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountTaxInfoRepository extends JpaRepository<AccountTaxInfo, Long> {
}
