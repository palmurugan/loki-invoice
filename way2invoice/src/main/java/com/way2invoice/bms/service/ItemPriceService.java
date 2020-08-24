package com.way2invoice.bms.service;

import com.way2invoice.bms.service.dto.ItemPriceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.way2invoice.bms.domain.ItemPrice}.
 */
public interface ItemPriceService {

    /**
     * Save a itemPrice.
     *
     * @param itemPriceDTO the entity to save.
     * @return the persisted entity.
     */
    ItemPriceDTO save(ItemPriceDTO itemPriceDTO);

    /**
     * Get all the itemPrices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemPriceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" itemPrice.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemPriceDTO> findOne(Long id);

    /**
     * Delete the "id" itemPrice.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
