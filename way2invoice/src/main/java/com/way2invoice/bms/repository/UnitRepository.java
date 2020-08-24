package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.Unit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Unit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
}
