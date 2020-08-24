package com.way2invoice.bms.service;

import com.way2invoice.bms.service.dto.CustomerCategoryDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.way2invoice.bms.domain.CustomerCategory}.
 */
public interface CustomerCategoryService {

    /**
     * Save a customerCategory.
     *
     * @param customerCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    CustomerCategoryDTO save(CustomerCategoryDTO customerCategoryDTO);

    /**
     * Get all the customerCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CustomerCategoryDTO> findAll(Pageable pageable);


    /**
     * Get the "id" customerCategory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CustomerCategoryDTO> findOne(Long id);

    /**
     * Delete the "id" customerCategory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
