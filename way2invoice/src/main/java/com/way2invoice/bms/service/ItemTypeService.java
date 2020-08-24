package com.way2invoice.bms.service;

import com.way2invoice.bms.service.dto.ItemTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.way2invoice.bms.domain.ItemType}.
 */
public interface ItemTypeService {

    /**
     * Save a itemType.
     *
     * @param itemTypeDTO the entity to save.
     * @return the persisted entity.
     */
    ItemTypeDTO save(ItemTypeDTO itemTypeDTO);

    /**
     * Get all the itemTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" itemType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemTypeDTO> findOne(Long id);

    /**
     * Delete the "id" itemType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
