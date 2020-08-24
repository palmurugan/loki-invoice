package com.way2invoice.bms.service;

import com.way2invoice.bms.service.dto.AccountTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.way2invoice.bms.domain.AccountType}.
 */
public interface AccountTypeService {

    /**
     * Save a accountType.
     *
     * @param accountTypeDTO the entity to save.
     * @return the persisted entity.
     */
    AccountTypeDTO save(AccountTypeDTO accountTypeDTO);

    /**
     * Get all the accountTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AccountTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" accountType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountTypeDTO> findOne(Long id);

    /**
     * Delete the "id" accountType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
