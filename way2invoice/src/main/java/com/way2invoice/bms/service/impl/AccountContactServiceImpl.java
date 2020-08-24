package com.way2invoice.bms.service.impl;

import com.way2invoice.bms.service.AccountContactService;
import com.way2invoice.bms.domain.AccountContact;
import com.way2invoice.bms.repository.AccountContactRepository;
import com.way2invoice.bms.service.dto.AccountContactDTO;
import com.way2invoice.bms.service.mapper.AccountContactMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AccountContact}.
 */
@Service
@Transactional
public class AccountContactServiceImpl implements AccountContactService {

    private final Logger log = LoggerFactory.getLogger(AccountContactServiceImpl.class);

    private final AccountContactRepository accountContactRepository;

    private final AccountContactMapper accountContactMapper;

    public AccountContactServiceImpl(AccountContactRepository accountContactRepository, AccountContactMapper accountContactMapper) {
        this.accountContactRepository = accountContactRepository;
        this.accountContactMapper = accountContactMapper;
    }

    /**
     * Save a accountContact.
     *
     * @param accountContactDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AccountContactDTO save(AccountContactDTO accountContactDTO) {
        log.debug("Request to save AccountContact : {}", accountContactDTO);
        AccountContact accountContact = accountContactMapper.toEntity(accountContactDTO);
        accountContact = accountContactRepository.save(accountContact);
        return accountContactMapper.toDto(accountContact);
    }

    /**
     * Get all the accountContacts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AccountContactDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AccountContacts");
        return accountContactRepository.findAll(pageable)
            .map(accountContactMapper::toDto);
    }


    /**
     * Get one accountContact by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AccountContactDTO> findOne(Long id) {
        log.debug("Request to get AccountContact : {}", id);
        return accountContactRepository.findById(id)
            .map(accountContactMapper::toDto);
    }

    /**
     * Delete the accountContact by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccountContact : {}", id);

        accountContactRepository.deleteById(id);
    }
}
