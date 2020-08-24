package com.way2invoice.bms.service.impl;

import com.way2invoice.bms.service.CustomerCategoryService;
import com.way2invoice.bms.domain.CustomerCategory;
import com.way2invoice.bms.repository.CustomerCategoryRepository;
import com.way2invoice.bms.service.dto.CustomerCategoryDTO;
import com.way2invoice.bms.service.mapper.CustomerCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CustomerCategory}.
 */
@Service
@Transactional
public class CustomerCategoryServiceImpl implements CustomerCategoryService {

    private final Logger log = LoggerFactory.getLogger(CustomerCategoryServiceImpl.class);

    private final CustomerCategoryRepository customerCategoryRepository;

    private final CustomerCategoryMapper customerCategoryMapper;

    public CustomerCategoryServiceImpl(CustomerCategoryRepository customerCategoryRepository, CustomerCategoryMapper customerCategoryMapper) {
        this.customerCategoryRepository = customerCategoryRepository;
        this.customerCategoryMapper = customerCategoryMapper;
    }

    /**
     * Save a customerCategory.
     *
     * @param customerCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CustomerCategoryDTO save(CustomerCategoryDTO customerCategoryDTO) {
        log.debug("Request to save CustomerCategory : {}", customerCategoryDTO);
        CustomerCategory customerCategory = customerCategoryMapper.toEntity(customerCategoryDTO);
        customerCategory = customerCategoryRepository.save(customerCategory);
        return customerCategoryMapper.toDto(customerCategory);
    }

    /**
     * Get all the customerCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerCategories");
        return customerCategoryRepository.findAll(pageable)
            .map(customerCategoryMapper::toDto);
    }


    /**
     * Get one customerCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerCategoryDTO> findOne(Long id) {
        log.debug("Request to get CustomerCategory : {}", id);
        return customerCategoryRepository.findById(id)
            .map(customerCategoryMapper::toDto);
    }

    /**
     * Delete the customerCategory by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerCategory : {}", id);
        customerCategoryRepository.deleteById(id);
    }
}
