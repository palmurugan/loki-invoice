package com.way2invoice.bms.service;

import com.way2invoice.bms.common.response.WidgetData;
import com.way2invoice.bms.service.dto.AccountContactDTO;
import com.way2invoice.bms.service.dto.AccountsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.way2invoice.bms.domain.Accounts}.
 */
public interface AccountsService {

    /**
     * Save a accounts.
     *
     * @param accountsDTO the entity to save.
     * @return the persisted entity.
     */
    AccountsDTO save(AccountsDTO accountsDTO);

    /**
     * Get all the accounts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AccountsDTO> findAll(Pageable pageable);

    /**
     * Get all the accounts based on the type
     *
     * @param accountType
     * @param pageable    the pagination information.
     * @return the list of accounts
     */
    Page<AccountsDTO> findAll(String accountType, Pageable pageable);


    /**
     * Get the "id" accounts.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountsDTO> findOne(Long id);

    /**
     * Get the primary contact of the account
     *
     * @param accountId the accountId
     * @return the contact details
     */
    Optional<AccountContactDTO> findPrimaryContact(Long accountId);

    /**
     * Find the tax type GST/IGST
     *
     * @param accountId the accountId
     * @return the tax type (GST/IGST/Vat)
     */
    Optional<WidgetData> findTaxType(Long accountId);

    /**
     * Delete the "id" accounts.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
