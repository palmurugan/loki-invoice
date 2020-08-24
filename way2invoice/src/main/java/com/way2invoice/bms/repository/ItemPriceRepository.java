package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.ItemPrice;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ItemPrice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemPriceRepository extends JpaRepository<ItemPrice, Long> {
}
