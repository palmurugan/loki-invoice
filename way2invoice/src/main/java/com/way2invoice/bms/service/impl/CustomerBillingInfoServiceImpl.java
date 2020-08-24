package com.way2invoice.bms.service.impl;

import com.way2invoice.bms.service.CustomerBillingInfoService;
import com.way2invoice.bms.domain.CustomerBillingInfo;
import com.way2invoice.bms.repository.CustomerBillingInfoRepository;
import com.way2invoice.bms.service.dto.CustomerBillingInfoDTO;
import com.way2invoice.bms.service.mapper.CustomerBillingInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CustomerBillingInfo}.
 */
@Service
@Transactional
public class CustomerBillingInfoServiceImpl implements CustomerBillingInfoService {

    private final Logger log = LoggerFactory.getLogger(CustomerBillingInfoServiceImpl.class);

    private final CustomerBillingInfoRepository customerBillingInfoRepository;

    private final CustomerBillingInfoMapper customerBillingInfoMapper;

    public CustomerBillingInfoServiceImpl(CustomerBillingInfoRepository customerBillingInfoRepository, CustomerBillingInfoMapper customerBillingInfoMapper) {
        this.customerBillingInfoRepository = customerBillingInfoRepository;
        this.customerBillingInfoMapper = customerBillingInfoMapper;
    }

    /**
     * Save a customerBillingInfo.
     *
     * @param customerBillingInfoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CustomerBillingInfoDTO save(CustomerBillingInfoDTO customerBillingInfoDTO) {
        log.debug("Request to save CustomerBillingInfo : {}", customerBillingInfoDTO);
        CustomerBillingInfo customerBillingInfo = customerBillingInfoMapper.toEntity(customerBillingInfoDTO);
        customerBillingInfo = customerBillingInfoRepository.save(customerBillingInfo);
        return customerBillingInfoMapper.toDto(customerBillingInfo);
    }

    /**
     * Get all the customerBillingInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerBillingInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerBillingInfos");
        return customerBillingInfoRepository.findAll(pageable)
            .map(customerBillingInfoMapper::toDto);
    }


    /**
     * Get one customerBillingInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerBillingInfoDTO> findOne(Long id) {
        log.debug("Request to get CustomerBillingInfo : {}", id);
        return customerBillingInfoRepository.findById(id)
            .map(customerBillingInfoMapper::toDto);
    }

    /**
     * Delete the customerBillingInfo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerBillingInfo : {}", id);
        customerBillingInfoRepository.deleteById(id);
    }
}
