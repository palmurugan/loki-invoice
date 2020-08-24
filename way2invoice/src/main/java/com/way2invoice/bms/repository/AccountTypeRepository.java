package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.AccountType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AccountType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
}
