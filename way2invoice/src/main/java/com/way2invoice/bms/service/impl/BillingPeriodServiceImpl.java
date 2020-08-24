package com.way2invoice.bms.service.impl;

import com.way2invoice.bms.service.BillingPeriodService;
import com.way2invoice.bms.domain.BillingPeriod;
import com.way2invoice.bms.repository.BillingPeriodRepository;
import com.way2invoice.bms.service.dto.BillingPeriodDTO;
import com.way2invoice.bms.service.mapper.BillingPeriodMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BillingPeriod}.
 */
@Service
@Transactional
public class BillingPeriodServiceImpl implements BillingPeriodService {

    private final Logger log = LoggerFactory.getLogger(BillingPeriodServiceImpl.class);

    private final BillingPeriodRepository billingPeriodRepository;

    private final BillingPeriodMapper billingPeriodMapper;

    public BillingPeriodServiceImpl(BillingPeriodRepository billingPeriodRepository, BillingPeriodMapper billingPeriodMapper) {
        this.billingPeriodRepository = billingPeriodRepository;
        this.billingPeriodMapper = billingPeriodMapper;
    }

    /**
     * Save a billingPeriod.
     *
     * @param billingPeriodDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BillingPeriodDTO save(BillingPeriodDTO billingPeriodDTO) {
        log.debug("Request to save BillingPeriod : {}", billingPeriodDTO);
        BillingPeriod billingPeriod = billingPeriodMapper.toEntity(billingPeriodDTO);
        billingPeriod = billingPeriodRepository.save(billingPeriod);
        return billingPeriodMapper.toDto(billingPeriod);
    }

    /**
     * Get all the billingPeriods.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BillingPeriodDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BillingPeriods");
        return billingPeriodRepository.findAll(pageable)
            .map(billingPeriodMapper::toDto);
    }


    /**
     * Get one billingPeriod by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BillingPeriodDTO> findOne(Long id) {
        log.debug("Request to get BillingPeriod : {}", id);
        return billingPeriodRepository.findById(id)
            .map(billingPeriodMapper::toDto);
    }

    /**
     * Delete the billingPeriod by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BillingPeriod : {}", id);
        billingPeriodRepository.deleteById(id);
    }
}
