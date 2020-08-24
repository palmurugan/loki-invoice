package com.way2invoice.bms.service;

import com.way2invoice.bms.service.dto.BillingTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.way2invoice.bms.domain.BillingType}.
 */
public interface BillingTypeService {

    /**
     * Save a billingType.
     *
     * @param billingTypeDTO the entity to save.
     * @return the persisted entity.
     */
    BillingTypeDTO save(BillingTypeDTO billingTypeDTO);

    /**
     * Get all the billingTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BillingTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" billingType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BillingTypeDTO> findOne(Long id);

    /**
     * Delete the "id" billingType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
