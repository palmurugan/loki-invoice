package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.CustomerContact;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CustomerContact entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerContactRepository extends JpaRepository<CustomerContact, Long> {
}
