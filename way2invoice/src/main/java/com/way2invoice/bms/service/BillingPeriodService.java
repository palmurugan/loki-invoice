package com.way2invoice.bms.service;

import com.way2invoice.bms.service.dto.BillingPeriodDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.way2invoice.bms.domain.BillingPeriod}.
 */
public interface BillingPeriodService {

    /**
     * Save a billingPeriod.
     *
     * @param billingPeriodDTO the entity to save.
     * @return the persisted entity.
     */
    BillingPeriodDTO save(BillingPeriodDTO billingPeriodDTO);

    /**
     * Get all the billingPeriods.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BillingPeriodDTO> findAll(Pageable pageable);


    /**
     * Get the "id" billingPeriod.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BillingPeriodDTO> findOne(Long id);

    /**
     * Delete the "id" billingPeriod.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
