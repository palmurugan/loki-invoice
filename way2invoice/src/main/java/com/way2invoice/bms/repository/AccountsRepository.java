package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.Accounts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Accounts entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Page<Accounts> findByAccountTypeName(String accountType, Pageable pageable);
}
