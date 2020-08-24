package com.way2invoice.bms.service.impl;

import com.way2invoice.bms.service.AccountGroupService;
import com.way2invoice.bms.domain.AccountGroup;
import com.way2invoice.bms.repository.AccountGroupRepository;
import com.way2invoice.bms.service.dto.AccountGroupDTO;
import com.way2invoice.bms.service.mapper.AccountGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AccountGroup}.
 */
@Service
@Transactional
public class AccountGroupServiceImpl implements AccountGroupService {

    private final Logger log = LoggerFactory.getLogger(AccountGroupServiceImpl.class);

    private final AccountGroupRepository accountGroupRepository;

    private final AccountGroupMapper accountGroupMapper;

    public AccountGroupServiceImpl(AccountGroupRepository accountGroupRepository, AccountGroupMapper accountGroupMapper) {
        this.accountGroupRepository = accountGroupRepository;
        this.accountGroupMapper = accountGroupMapper;
    }

    /**
     * Save a accountGroup.
     *
     * @param accountGroupDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AccountGroupDTO save(AccountGroupDTO accountGroupDTO) {
        log.debug("Request to save AccountGroup : {}", accountGroupDTO);
        AccountGroup accountGroup = accountGroupMapper.toEntity(accountGroupDTO);
        accountGroup = accountGroupRepository.save(accountGroup);
        return accountGroupMapper.toDto(accountGroup);
    }

    /**
     * Get all the accountGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AccountGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AccountGroups");
        return accountGroupRepository.findAll(pageable)
            .map(accountGroupMapper::toDto);
    }


    /**
     * Get one accountGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AccountGroupDTO> findOne(Long id) {
        log.debug("Request to get AccountGroup : {}", id);
        return accountGroupRepository.findById(id)
            .map(accountGroupMapper::toDto);
    }

    /**
     * Delete the accountGroup by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccountGroup : {}", id);

        accountGroupRepository.deleteById(id);
    }
}
