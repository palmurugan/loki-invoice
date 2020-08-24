package com.way2invoice.bms.service;

import com.way2invoice.bms.service.dto.AccountTaxInfoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.way2invoice.bms.domain.AccountTaxInfo}.
 */
public interface AccountTaxInfoService {

    /**
     * Save a accountTaxInfo.
     *
     * @param accountTaxInfoDTO the entity to save.
     * @return the persisted entity.
     */
    AccountTaxInfoDTO save(AccountTaxInfoDTO accountTaxInfoDTO);

    /**
     * Get all the accountTaxInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AccountTaxInfoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" accountTaxInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountTaxInfoDTO> findOne(Long id);

    /**
     * Delete the "id" accountTaxInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
