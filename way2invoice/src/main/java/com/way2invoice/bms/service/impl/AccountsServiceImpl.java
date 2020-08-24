package com.way2invoice.bms.service.impl;

import com.way2invoice.bms.common.response.WidgetData;
import com.way2invoice.bms.domain.AccountContact;
import com.way2invoice.bms.domain.Accounts;
import com.way2invoice.bms.domain.Company;
import com.way2invoice.bms.domain.State;
import com.way2invoice.bms.repository.AccountsRepository;
import com.way2invoice.bms.repository.CompanyRepository;
import com.way2invoice.bms.service.AccountsService;
import com.way2invoice.bms.service.dto.AccountContactDTO;
import com.way2invoice.bms.service.dto.AccountsDTO;
import com.way2invoice.bms.service.mapper.AccountsMapper;
import com.way2invoice.bms.util.ApplicationUtil;
import com.way2invoice.bms.web.rest.errors.BadRequestAlertException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Accounts}.
 */
@Service
@Transactional
public class AccountsServiceImpl implements AccountsService {

    private final Logger log = LoggerFactory.getLogger(AccountsServiceImpl.class);

    private final AccountsRepository accountsRepository;

    private final CompanyRepository companyRepository;

    private final AccountsMapper accountsMapper;

    public AccountsServiceImpl(AccountsRepository accountsRepository,
        AccountsMapper accountsMapper, CompanyRepository companyRepository) {
        this.accountsRepository = accountsRepository;
        this.accountsMapper = accountsMapper;
        this.companyRepository = companyRepository;
    }

    /**
     * Save a accounts.
     *
     * @param accountsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AccountsDTO save(AccountsDTO accountsDTO) {
        log.debug("Request to save Accounts : {}", accountsDTO);
        Accounts accounts = accountsMapper.toEntity(accountsDTO);
        accounts = accountsRepository.save(accounts);
        return accountsMapper.toDto(accounts);
    }

    /**
     * Get all the accounts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AccountsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Accounts");
        return accountsRepository.findAll(pageable)
            .map(accountsMapper::toDto);
    }

    /**
     * Get all the accounts based on the accountType
     *
     * @param accountType
     * @param pageable    the pagination information.
     * @return the list of accounts
     */
    @Override
    public Page<AccountsDTO> findAll(String accountType,
        Pageable pageable) {
        log.debug("Request to get all the accounts based on the account type {}", accountType);
        return accountsRepository.findByAccountTypeName(accountType, pageable)
            .map(accountsMapper::toDto);
    }

    /**
     * Get one accounts by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AccountsDTO> findOne(Long id) {
        log.debug("Request to get Accounts : {}", id);
        return accountsRepository.findById(id)
            .map(accountsMapper::toDto);
    }

    /**
     * Get the primary account details
     *
     * @param accountId the accountId
     * @return the contact details
     */
    @Override
    public Optional<AccountContactDTO> findPrimaryContact(Long accountId) {
        Optional<AccountsDTO> accountsDTO = findOne(accountId);
        return accountsDTO.flatMap(dto -> dto.getAccountContacts().stream().findFirst());
    }

    /**
     * Find the tax type of the account (Customer/Vendor)
     *
     * @param accountId the accountId
     * @return the {@link WidgetData} with tax type
     */
    @Override
    public Optional<WidgetData> findTaxType(Long accountId) {
        Optional<Company> company = companyRepository.findById(1L);
        Optional<Accounts> account = accountsRepository.findById(accountId);
        if (!company.isPresent() || !account.isPresent()) {
            throw new BadRequestAlertException("Invalid Contact Information", "Accounts",
                "account.invalid.contact");
        }
        State companyState = company.get().getState();
        Optional<AccountContact> accountContact = account.get().getAccountContacts().stream()
            .findFirst();
        Long accountsStateId = accountContact.isPresent() ? accountContact.get().getState().getId()
            : companyState.getId();
        return ApplicationUtil
            .findTaxType(companyState.getId(), accountsStateId);
    }

    /**
     * Delete the accounts by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Accounts : {}", id);

        accountsRepository.deleteById(id);
    }
}
