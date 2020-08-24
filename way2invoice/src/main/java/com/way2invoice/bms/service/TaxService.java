package com.way2invoice.bms.service;

import com.way2invoice.bms.service.dto.TaxDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.way2invoice.bms.domain.Tax}.
 */
public interface TaxService {

    /**
     * Save a tax.
     *
     * @param taxDTO the entity to save.
     * @return the persisted entity.
     */
    TaxDTO save(TaxDTO taxDTO);

    /**
     * Get all the taxes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TaxDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tax.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaxDTO> findOne(Long id);

    /**
     * Delete the "id" tax.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
