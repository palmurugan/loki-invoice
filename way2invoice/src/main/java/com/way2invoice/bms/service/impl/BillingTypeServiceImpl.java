package com.way2invoice.bms.service.impl;

import com.way2invoice.bms.service.BillingTypeService;
import com.way2invoice.bms.domain.BillingType;
import com.way2invoice.bms.repository.BillingTypeRepository;
import com.way2invoice.bms.service.dto.BillingTypeDTO;
import com.way2invoice.bms.service.mapper.BillingTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BillingType}.
 */
@Service
@Transactional
public class BillingTypeServiceImpl implements BillingTypeService {

    private final Logger log = LoggerFactory.getLogger(BillingTypeServiceImpl.class);

    private final BillingTypeRepository billingTypeRepository;

    private final BillingTypeMapper billingTypeMapper;

    public BillingTypeServiceImpl(BillingTypeRepository billingTypeRepository, BillingTypeMapper billingTypeMapper) {
        this.billingTypeRepository = billingTypeRepository;
        this.billingTypeMapper = billingTypeMapper;
    }

    /**
     * Save a billingType.
     *
     * @param billingTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BillingTypeDTO save(BillingTypeDTO billingTypeDTO) {
        log.debug("Request to save BillingType : {}", billingTypeDTO);
        BillingType billingType = billingTypeMapper.toEntity(billingTypeDTO);
        billingType = billingTypeRepository.save(billingType);
        return billingTypeMapper.toDto(billingType);
    }

    /**
     * Get all the billingTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BillingTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BillingTypes");
        return billingTypeRepository.findAll(pageable)
            .map(billingTypeMapper::toDto);
    }


    /**
     * Get one billingType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BillingTypeDTO> findOne(Long id) {
        log.debug("Request to get BillingType : {}", id);
        return billingTypeRepository.findById(id)
            .map(billingTypeMapper::toDto);
    }

    /**
     * Delete the billingType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BillingType : {}", id);
        billingTypeRepository.deleteById(id);
    }
}
