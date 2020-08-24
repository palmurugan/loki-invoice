package com.way2invoice.bms.service;

import com.way2invoice.bms.service.dto.AccountContactDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.way2invoice.bms.domain.AccountContact}.
 */
public interface AccountContactService {

    /**
     * Save a accountContact.
     *
     * @param accountContactDTO the entity to save.
     * @return the persisted entity.
     */
    AccountContactDTO save(AccountContactDTO accountContactDTO);

    /**
     * Get all the accountContacts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AccountContactDTO> findAll(Pageable pageable);


    /**
     * Get the "id" accountContact.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountContactDTO> findOne(Long id);

    /**
     * Delete the "id" accountContact.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
