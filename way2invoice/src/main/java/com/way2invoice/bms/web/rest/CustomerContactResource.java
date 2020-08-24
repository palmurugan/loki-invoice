package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.domain.enumeration.Status;
import com.way2invoice.bms.service.CustomerContactService;
import com.way2invoice.bms.web.rest.errors.BadRequestAlertException;
import com.way2invoice.bms.service.dto.CustomerContactDTO;

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
 * REST controller for managing {@link com.way2invoice.bms.domain.CustomerContact}.
 */
@RestController
@RequestMapping("/api")
public class CustomerContactResource {

    private final Logger log = LoggerFactory.getLogger(CustomerContactResource.class);

    private static final String ENTITY_NAME = "customerContact";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomerContactService customerContactService;

    public CustomerContactResource(CustomerContactService customerContactService) {
        this.customerContactService = customerContactService;
    }

    /**
     * {@code POST  /customer-contacts} : Create a new customerContact.
     *
     * @param customerContactDTO the customerContactDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customerContactDTO, or with status {@code 400 (Bad Request)} if the customerContact has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/customer-contacts")
    public ResponseEntity<CustomerContactDTO> createCustomerContact(@Valid @RequestBody CustomerContactDTO customerContactDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerContact : {}", customerContactDTO);
        if (customerContactDTO.getId() != null) {
            throw new BadRequestAlertException("A new customerContact cannot already have an ID", ENTITY_NAME, "idexists");
        }
        customerContactDTO.setStatus(Status.ACTIVE);
        CustomerContactDTO result = customerContactService.save(customerContactDTO);
        return ResponseEntity.created(new URI("/api/customer-contacts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /customer-contacts} : Updates an existing customerContact.
     *
     * @param customerContactDTO the customerContactDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerContactDTO,
     * or with status {@code 400 (Bad Request)} if the customerContactDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customerContactDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/customer-contacts")
    public ResponseEntity<CustomerContactDTO> updateCustomerContact(@Valid @RequestBody CustomerContactDTO customerContactDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerContact : {}", customerContactDTO);
        if (customerContactDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        customerContactDTO.setStatus(Status.ACTIVE);
        CustomerContactDTO result = customerContactService.save(customerContactDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customerContactDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /customer-contacts} : get all the customerContacts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customerContacts in body.
     */
    @GetMapping("/customer-contacts")
    public ResponseEntity<List<CustomerContactDTO>> getAllCustomerContacts(Pageable pageable) {
        log.debug("REST request to get a page of CustomerContacts");
        Page<CustomerContactDTO> page = customerContactService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /customer-contacts/:id} : get the "id" customerContact.
     *
     * @param id the id of the customerContactDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customerContactDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customer-contacts/{id}")
    public ResponseEntity<CustomerContactDTO> getCustomerContact(@PathVariable Long id) {
        log.debug("REST request to get CustomerContact : {}", id);
        Optional<CustomerContactDTO> customerContactDTO = customerContactService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerContactDTO);
    }

    /**
     * {@code DELETE  /customer-contacts/:id} : delete the "id" customerContact.
     *
     * @param id the id of the customerContactDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customer-contacts/{id}")
    public ResponseEntity<Void> deleteCustomerContact(@PathVariable Long id) {
        log.debug("REST request to delete CustomerContact : {}", id);
        customerContactService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
