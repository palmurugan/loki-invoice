package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.service.BillingPeriodService;
import com.way2invoice.bms.web.rest.errors.BadRequestAlertException;
import com.way2invoice.bms.service.dto.BillingPeriodDTO;

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
 * REST controller for managing {@link com.way2invoice.bms.domain.BillingPeriod}.
 */
@RestController
@RequestMapping("/api")
public class BillingPeriodResource {

    private final Logger log = LoggerFactory.getLogger(BillingPeriodResource.class);

    private static final String ENTITY_NAME = "billingPeriod";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillingPeriodService billingPeriodService;

    public BillingPeriodResource(BillingPeriodService billingPeriodService) {
        this.billingPeriodService = billingPeriodService;
    }

    /**
     * {@code POST  /billing-periods} : Create a new billingPeriod.
     *
     * @param billingPeriodDTO the billingPeriodDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billingPeriodDTO, or with status {@code 400 (Bad Request)} if the billingPeriod has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/billing-periods")
    public ResponseEntity<BillingPeriodDTO> createBillingPeriod(@Valid @RequestBody BillingPeriodDTO billingPeriodDTO) throws URISyntaxException {
        log.debug("REST request to save BillingPeriod : {}", billingPeriodDTO);
        if (billingPeriodDTO.getId() != null) {
            throw new BadRequestAlertException("A new billingPeriod cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BillingPeriodDTO result = billingPeriodService.save(billingPeriodDTO);
        return ResponseEntity.created(new URI("/api/billing-periods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /billing-periods} : Updates an existing billingPeriod.
     *
     * @param billingPeriodDTO the billingPeriodDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billingPeriodDTO,
     * or with status {@code 400 (Bad Request)} if the billingPeriodDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the billingPeriodDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/billing-periods")
    public ResponseEntity<BillingPeriodDTO> updateBillingPeriod(@Valid @RequestBody BillingPeriodDTO billingPeriodDTO) throws URISyntaxException {
        log.debug("REST request to update BillingPeriod : {}", billingPeriodDTO);
        if (billingPeriodDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BillingPeriodDTO result = billingPeriodService.save(billingPeriodDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, billingPeriodDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /billing-periods} : get all the billingPeriods.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billingPeriods in body.
     */
    @GetMapping("/billing-periods")
    public ResponseEntity<List<BillingPeriodDTO>> getAllBillingPeriods(Pageable pageable) {
        log.debug("REST request to get a page of BillingPeriods");
        Page<BillingPeriodDTO> page = billingPeriodService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /billing-periods/:id} : get the "id" billingPeriod.
     *
     * @param id the id of the billingPeriodDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billingPeriodDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/billing-periods/{id}")
    public ResponseEntity<BillingPeriodDTO> getBillingPeriod(@PathVariable Long id) {
        log.debug("REST request to get BillingPeriod : {}", id);
        Optional<BillingPeriodDTO> billingPeriodDTO = billingPeriodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(billingPeriodDTO);
    }

    /**
     * {@code DELETE  /billing-periods/:id} : delete the "id" billingPeriod.
     *
     * @param id the id of the billingPeriodDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/billing-periods/{id}")
    public ResponseEntity<Void> deleteBillingPeriod(@PathVariable Long id) {
        log.debug("REST request to delete BillingPeriod : {}", id);
        billingPeriodService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
