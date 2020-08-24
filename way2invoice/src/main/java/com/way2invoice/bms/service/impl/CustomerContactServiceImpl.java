package com.way2invoice.bms.service.impl;

import com.way2invoice.bms.service.CustomerContactService;
import com.way2invoice.bms.domain.CustomerContact;
import com.way2invoice.bms.repository.CustomerContactRepository;
import com.way2invoice.bms.service.dto.CustomerContactDTO;
import com.way2invoice.bms.service.mapper.CustomerContactMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CustomerContact}.
 */
@Service
@Transactional
public class CustomerContactServiceImpl implements CustomerContactService {

    private final Logger log = LoggerFactory.getLogger(CustomerContactServiceImpl.class);

    private final CustomerContactRepository customerContactRepository;

    private final CustomerContactMapper customerContactMapper;

    public CustomerContactServiceImpl(CustomerContactRepository customerContactRepository, CustomerContactMapper customerContactMapper) {
        this.customerContactRepository = customerContactRepository;
        this.customerContactMapper = customerContactMapper;
    }

    /**
     * Save a customerContact.
     *
     * @param customerContactDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CustomerContactDTO save(CustomerContactDTO customerContactDTO) {
        log.debug("Request to save CustomerContact : {}", customerContactDTO);
        CustomerContact customerContact = customerContactMapper.toEntity(customerContactDTO);
        customerContact = customerContactRepository.save(customerContact);
        return customerContactMapper.toDto(customerContact);
    }

    /**
     * Get all the customerContacts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerContactDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerContacts");
        return customerContactRepository.findAll(pageable)
            .map(customerContactMapper::toDto);
    }


    /**
     * Get one customerContact by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerContactDTO> findOne(Long id) {
        log.debug("Request to get CustomerContact : {}", id);
        return customerContactRepository.findById(id)
            .map(customerContactMapper::toDto);
    }

    /**
     * Delete the customerContact by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerContact : {}", id);
        customerContactRepository.deleteById(id);
    }
}
