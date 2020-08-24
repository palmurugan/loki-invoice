package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.domain.enumeration.Status;
import com.way2invoice.bms.service.CustomerBillingInfoService;
import com.way2invoice.bms.web.rest.errors.BadRequestAlertException;
import com.way2invoice.bms.service.dto.CustomerBillingInfoDTO;

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
 * REST controller for managing {@link com.way2invoice.bms.domain.CustomerBillingInfo}.
 */
@RestController
@RequestMapping("/api")
public class CustomerBillingInfoResource {

    private final Logger log = LoggerFactory.getLogger(CustomerBillingInfoResource.class);

    private static final String ENTITY_NAME = "customerBillingInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomerBillingInfoService customerBillingInfoService;

    public CustomerBillingInfoResource(CustomerBillingInfoService customerBillingInfoService) {
        this.customerBillingInfoService = customerBillingInfoService;
    }

    /**
     * {@code POST  /customer-billing-infos} : Create a new customerBillingInfo.
     *
     * @param customerBillingInfoDTO the customerBillingInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customerBillingInfoDTO, or with status {@code 400 (Bad Request)} if the customerBillingInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/customer-billing-infos")
    public ResponseEntity<CustomerBillingInfoDTO> createCustomerBillingInfo(@Valid @RequestBody CustomerBillingInfoDTO customerBillingInfoDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerBillingInfo : {}", customerBillingInfoDTO);
        if (customerBillingInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new customerBillingInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        customerBillingInfoDTO.setStatus(Status.ACTIVE);
        CustomerBillingInfoDTO result = customerBillingInfoService.save(customerBillingInfoDTO);
        return ResponseEntity.created(new URI("/api/customer-billing-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /customer-billing-infos} : Updates an existing customerBillingInfo.
     *
     * @param customerBillingInfoDTO the customerBillingInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerBillingInfoDTO,
     * or with status {@code 400 (Bad Request)} if the customerBillingInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customerBillingInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/customer-billing-infos")
    public ResponseEntity<CustomerBillingInfoDTO> updateCustomerBillingInfo(@Valid @RequestBody CustomerBillingInfoDTO customerBillingInfoDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerBillingInfo : {}", customerBillingInfoDTO);
        if (customerBillingInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        customerBillingInfoDTO.setStatus(Status.ACTIVE);
        CustomerBillingInfoDTO result = customerBillingInfoService.save(customerBillingInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customerBillingInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /customer-billing-infos} : get all the customerBillingInfos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customerBillingInfos in body.
     */
    @GetMapping("/customer-billing-infos")
    public ResponseEntity<List<CustomerBillingInfoDTO>> getAllCustomerBillingInfos(Pageable pageable) {
        log.debug("REST request to get a page of CustomerBillingInfos");
        Page<CustomerBillingInfoDTO> page = customerBillingInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /customer-billing-infos/:id} : get the "id" customerBillingInfo.
     *
     * @param id the id of the customerBillingInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customerBillingInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customer-billing-infos/{id}")
    public ResponseEntity<CustomerBillingInfoDTO> getCustomerBillingInfo(@PathVariable Long id) {
        log.debug("REST request to get CustomerBillingInfo : {}", id);
        Optional<CustomerBillingInfoDTO> customerBillingInfoDTO = customerBillingInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerBillingInfoDTO);
    }

    /**
     * {@code DELETE  /customer-billing-infos/:id} : delete the "id" customerBillingInfo.
     *
     * @param id the id of the customerBillingInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customer-billing-infos/{id}")
    public ResponseEntity<Void> deleteCustomerBillingInfo(@PathVariable Long id) {
        log.debug("REST request to delete CustomerBillingInfo : {}", id);
        customerBillingInfoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
