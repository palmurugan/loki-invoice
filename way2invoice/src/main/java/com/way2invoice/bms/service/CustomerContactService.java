package com.way2invoice.bms.service;

import com.way2invoice.bms.service.dto.CustomerContactDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.way2invoice.bms.domain.CustomerContact}.
 */
public interface CustomerContactService {

    /**
     * Save a customerContact.
     *
     * @param customerContactDTO the entity to save.
     * @return the persisted entity.
     */
    CustomerContactDTO save(CustomerContactDTO customerContactDTO);

    /**
     * Get all the customerContacts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CustomerContactDTO> findAll(Pageable pageable);


    /**
     * Get the "id" customerContact.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CustomerContactDTO> findOne(Long id);

    /**
     * Delete the "id" customerContact.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
