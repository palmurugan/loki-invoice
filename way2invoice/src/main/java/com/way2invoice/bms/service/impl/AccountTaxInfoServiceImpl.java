package com.way2invoice.bms.service.impl;

import com.way2invoice.bms.service.AccountTaxInfoService;
import com.way2invoice.bms.domain.AccountTaxInfo;
import com.way2invoice.bms.repository.AccountTaxInfoRepository;
import com.way2invoice.bms.service.dto.AccountTaxInfoDTO;
import com.way2invoice.bms.service.mapper.AccountTaxInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AccountTaxInfo}.
 */
@Service
@Transactional
public class AccountTaxInfoServiceImpl implements AccountTaxInfoService {

    private final Logger log = LoggerFactory.getLogger(AccountTaxInfoServiceImpl.class);

    private final AccountTaxInfoRepository accountTaxInfoRepository;

    private final AccountTaxInfoMapper accountTaxInfoMapper;

    public AccountTaxInfoServiceImpl(AccountTaxInfoRepository accountTaxInfoRepository, AccountTaxInfoMapper accountTaxInfoMapper) {
        this.accountTaxInfoRepository = accountTaxInfoRepository;
        this.accountTaxInfoMapper = accountTaxInfoMapper;
    }

    /**
     * Save a accountTaxInfo.
     *
     * @param accountTaxInfoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AccountTaxInfoDTO save(AccountTaxInfoDTO accountTaxInfoDTO) {
        log.debug("Request to save AccountTaxInfo : {}", accountTaxInfoDTO);
        AccountTaxInfo accountTaxInfo = accountTaxInfoMapper.toEntity(accountTaxInfoDTO);
        accountTaxInfo = accountTaxInfoRepository.save(accountTaxInfo);
        return accountTaxInfoMapper.toDto(accountTaxInfo);
    }

    /**
     * Get all the accountTaxInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AccountTaxInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AccountTaxInfos");
        return accountTaxInfoRepository.findAll(pageable)
            .map(accountTaxInfoMapper::toDto);
    }


    /**
     * Get one accountTaxInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AccountTaxInfoDTO> findOne(Long id) {
        log.debug("Request to get AccountTaxInfo : {}", id);
        return accountTaxInfoRepository.findById(id)
            .map(accountTaxInfoMapper::toDto);
    }

    /**
     * Delete the accountTaxInfo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccountTaxInfo : {}", id);

        accountTaxInfoRepository.deleteById(id);
    }
}
