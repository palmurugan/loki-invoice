package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.CustomerCategory;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CustomerCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerCategoryRepository extends JpaRepository<CustomerCategory, Long> {
}
