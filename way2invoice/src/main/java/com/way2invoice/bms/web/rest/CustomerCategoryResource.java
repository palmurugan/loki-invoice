package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.service.CustomerCategoryService;
import com.way2invoice.bms.web.rest.errors.BadRequestAlertException;
import com.way2invoice.bms.service.dto.CustomerCategoryDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.way2invoice.bms.domain.CustomerCategory}.
 */
@RestController
@RequestMapping("/api")
public class CustomerCategoryResource {

    private final Logger log = LoggerFactory.getLogger(CustomerCategoryResource.class);

    private static final String ENTITY_NAME = "customerCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomerCategoryService customerCategoryService;

    public CustomerCategoryResource(CustomerCategoryService customerCategoryService) {
        this.customerCategoryService = customerCategoryService;
    }

    /**
     * {@code POST  /customer-categories} : Create a new customerCategory.
     *
     * @param customerCategoryDTO the customerCategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customerCategoryDTO, or with status {@code 400 (Bad Request)} if the customerCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/customer-categories")
    public ResponseEntity<CustomerCategoryDTO> createCustomerCategory(@Valid @RequestBody CustomerCategoryDTO customerCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerCategory : {}", customerCategoryDTO);
        if (customerCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new customerCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerCategoryDTO result = customerCategoryService.save(customerCategoryDTO);
        return ResponseEntity.created(new URI("/api/customer-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /customer-categories} : Updates an existing customerCategory.
     *
     * @param customerCategoryDTO the customerCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the customerCategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customerCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/customer-categories")
    public ResponseEntity<CustomerCategoryDTO> updateCustomerCategory(@Valid @RequestBody CustomerCategoryDTO customerCategoryDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerCategory : {}", customerCategoryDTO);
        if (customerCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustomerCategoryDTO result = customerCategoryService.save(customerCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customerCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /customer-categories} : get all the customerCategories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customerCategories in body.
     */
    @GetMapping("/customer-categories")
    public ResponseEntity<List<CustomerCategoryDTO>> getAllCustomerCategories(Pageable pageable) {
        log.debug("REST request to get a page of CustomerCategories");
        Page<CustomerCategoryDTO> page = customerCategoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /customer-categories/:id} : get the "id" customerCategory.
     *
     * @param id the id of the customerCategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customerCategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customer-categories/{id}")
    public ResponseEntity<CustomerCategoryDTO> getCustomerCategory(@PathVariable Long id) {
        log.debug("REST request to get CustomerCategory : {}", id);
        Optional<CustomerCategoryDTO> customerCategoryDTO = customerCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerCategoryDTO);
    }

    /**
     * {@code DELETE  /customer-categories/:id} : delete the "id" customerCategory.
     *
     * @param id the id of the customerCategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customer-categories/{id}")
    public ResponseEntity<Void> deleteCustomerCategory(@PathVariable Long id) {
        log.debug("REST request to delete CustomerCategory : {}", id);
        customerCategoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
