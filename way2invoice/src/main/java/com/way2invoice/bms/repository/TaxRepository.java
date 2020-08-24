package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.Tax;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Tax entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {
}
