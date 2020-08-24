package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.ItemPriceHistory;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ItemPriceHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemPriceHistoryRepository extends JpaRepository<ItemPriceHistory, Long> {
}
