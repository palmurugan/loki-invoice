package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.service.BillingTypeService;
import com.way2invoice.bms.web.rest.errors.BadRequestAlertException;
import com.way2invoice.bms.service.dto.BillingTypeDTO;

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
 * REST controller for managing {@link com.way2invoice.bms.domain.BillingType}.
 */
@RestController
@RequestMapping("/api")
public class BillingTypeResource {

    private final Logger log = LoggerFactory.getLogger(BillingTypeResource.class);

    private static final String ENTITY_NAME = "billingType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillingTypeService billingTypeService;

    public BillingTypeResource(BillingTypeService billingTypeService) {
        this.billingTypeService = billingTypeService;
    }

    /**
     * {@code POST  /billing-types} : Create a new billingType.
     *
     * @param billingTypeDTO the billingTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billingTypeDTO, or with status {@code 400 (Bad Request)} if the billingType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/billing-types")
    public ResponseEntity<BillingTypeDTO> createBillingType(@Valid @RequestBody BillingTypeDTO billingTypeDTO) throws URISyntaxException {
        log.debug("REST request to save BillingType : {}", billingTypeDTO);
        if (billingTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new billingType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BillingTypeDTO result = billingTypeService.save(billingTypeDTO);
        return ResponseEntity.created(new URI("/api/billing-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /billing-types} : Updates an existing billingType.
     *
     * @param billingTypeDTO the billingTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billingTypeDTO,
     * or with status {@code 400 (Bad Request)} if the billingTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the billingTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/billing-types")
    public ResponseEntity<BillingTypeDTO> updateBillingType(@Valid @RequestBody BillingTypeDTO billingTypeDTO) throws URISyntaxException {
        log.debug("REST request to update BillingType : {}", billingTypeDTO);
        if (billingTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BillingTypeDTO result = billingTypeService.save(billingTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, billingTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /billing-types} : get all the billingTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billingTypes in body.
     */
    @GetMapping("/billing-types")
    public ResponseEntity<List<BillingTypeDTO>> getAllBillingTypes(Pageable pageable) {
        log.debug("REST request to get a page of BillingTypes");
        Page<BillingTypeDTO> page = billingTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /billing-types/:id} : get the "id" billingType.
     *
     * @param id the id of the billingTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billingTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/billing-types/{id}")
    public ResponseEntity<BillingTypeDTO> getBillingType(@PathVariable Long id) {
        log.debug("REST request to get BillingType : {}", id);
        Optional<BillingTypeDTO> billingTypeDTO = billingTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(billingTypeDTO);
    }

    /**
     * {@code DELETE  /billing-types/:id} : delete the "id" billingType.
     *
     * @param id the id of the billingTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/billing-types/{id}")
    public ResponseEntity<Void> deleteBillingType(@PathVariable Long id) {
        log.debug("REST request to delete BillingType : {}", id);
        billingTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
