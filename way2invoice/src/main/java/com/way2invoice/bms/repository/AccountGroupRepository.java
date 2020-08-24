package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.AccountGroup;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AccountGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountGroupRepository extends JpaRepository<AccountGroup, Long> {
}
