package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.AccountContact;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AccountContact entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountContactRepository extends JpaRepository<AccountContact, Long> {
}
