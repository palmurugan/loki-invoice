package com.way2invoice.bms.service;

import com.way2invoice.bms.service.dto.AccountGroupDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.way2invoice.bms.domain.AccountGroup}.
 */
public interface AccountGroupService {

    /**
     * Save a accountGroup.
     *
     * @param accountGroupDTO the entity to save.
     * @return the persisted entity.
     */
    AccountGroupDTO save(AccountGroupDTO accountGroupDTO);

    /**
     * Get all the accountGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AccountGroupDTO> findAll(Pageable pageable);


    /**
     * Get the "id" accountGroup.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountGroupDTO> findOne(Long id);

    /**
     * Delete the "id" accountGroup.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
