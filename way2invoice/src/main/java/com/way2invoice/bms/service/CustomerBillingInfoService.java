package com.way2invoice.bms.service;

import com.way2invoice.bms.service.dto.CustomerBillingInfoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.way2invoice.bms.domain.CustomerBillingInfo}.
 */
public interface CustomerBillingInfoService {

    /**
     * Save a customerBillingInfo.
     *
     * @param customerBillingInfoDTO the entity to save.
     * @return the persisted entity.
     */
    CustomerBillingInfoDTO save(CustomerBillingInfoDTO customerBillingInfoDTO);

    /**
     * Get all the customerBillingInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CustomerBillingInfoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" customerBillingInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CustomerBillingInfoDTO> findOne(Long id);

    /**
     * Delete the "id" customerBillingInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
