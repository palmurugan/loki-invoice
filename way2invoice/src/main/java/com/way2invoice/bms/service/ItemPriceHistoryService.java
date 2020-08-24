package com.way2invoice.bms.service;

import com.way2invoice.bms.service.dto.ItemPriceHistoryDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.way2invoice.bms.domain.ItemPriceHistory}.
 */
public interface ItemPriceHistoryService {

    /**
     * Save a itemPriceHistory.
     *
     * @param itemPriceHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    ItemPriceHistoryDTO save(ItemPriceHistoryDTO itemPriceHistoryDTO);

    /**
     * Get all the itemPriceHistories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemPriceHistoryDTO> findAll(Pageable pageable);


    /**
     * Get the "id" itemPriceHistory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemPriceHistoryDTO> findOne(Long id);

    /**
     * Delete the "id" itemPriceHistory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
