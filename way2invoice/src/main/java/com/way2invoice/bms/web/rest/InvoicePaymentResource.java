package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.domain.enumeration.InvoiceType;
import com.way2invoice.bms.service.InvoicePaymentService;
import com.way2invoice.bms.service.dto.InvoicePaymentDTO;
import com.way2invoice.bms.util.ApplicationConstants;
import com.way2invoice.bms.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link com.way2invoice.bms.domain.InvoicePayment}.
 */
@RestController
@RequestMapping("/api")
public class InvoicePaymentResource {

    private static final String ENTITY_NAME = "invoicePayment";
    private final Logger log = LoggerFactory.getLogger(InvoicePaymentResource.class);
    private final InvoicePaymentService invoicePaymentService;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public InvoicePaymentResource(InvoicePaymentService invoicePaymentService) {
        this.invoicePaymentService = invoicePaymentService;
    }

    /**
     * {@code POST  /invoice-payments} : Create a new invoicePayment.
     *
     * @param invoicePaymentDTO the invoicePaymentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new
     * invoicePaymentDTO, or with status {@code 400 (Bad Request)} if the invoicePayment has already
     * an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invoice-payments")
    public ResponseEntity<InvoicePaymentDTO> createInvoicePayment(
        @Valid @RequestBody InvoicePaymentDTO invoicePaymentDTO) throws URISyntaxException {
        log.debug("REST request to save InvoicePayment : {}", invoicePaymentDTO);
        if (invoicePaymentDTO.getId() != null) {
            throw new BadRequestAlertException("A new invoicePayment cannot already have an ID",
                ENTITY_NAME, "idexists");
        }
        InvoicePaymentDTO result = invoicePaymentService.save(invoicePaymentDTO);
        return ResponseEntity.created(new URI("/api/invoice-payments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME,
                result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /invoice-payments} : Updates an existing invoicePayment.
     *
     * @param invoicePaymentDTO the invoicePaymentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated
     * invoicePaymentDTO, or with status {@code 400 (Bad Request)} if the invoicePaymentDTO is not
     * valid, or with status {@code 500 (Internal Server Error)} if the invoicePaymentDTO couldn't
     * be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invoice-payments")
    public ResponseEntity<InvoicePaymentDTO> updateInvoicePayment(
        @Valid @RequestBody InvoicePaymentDTO invoicePaymentDTO) throws URISyntaxException {
        log.debug("REST request to update InvoicePayment : {}", invoicePaymentDTO);
        if (invoicePaymentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvoicePaymentDTO result = invoicePaymentService.save(invoicePaymentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                invoicePaymentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /invoice-payments} : get all the invoicePayments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of
     * invoicePayments in body.
     */
    @GetMapping("/invoice-payments")
    public ResponseEntity<List<InvoicePaymentDTO>> getAllInvoicePayments(
        @RequestParam String invoiceType, Pageable pageable) {
        log.debug("REST request to get a page of InvoicePayments");
        Page<InvoicePaymentDTO> page = StringUtils.isNotEmpty(invoiceType) && invoiceType.equals(
            ApplicationConstants.PURCHASE) ? invoicePaymentService.findAll(InvoiceType.P, pageable)
            : invoicePaymentService.findAll(InvoiceType.S, pageable);
        HttpHeaders headers = PaginationUtil
            .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /invoice-payments/:id} : get the "id" invoicePayment.
     *
     * @param id the id of the invoicePaymentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the
     * invoicePaymentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invoice-payments/{id}")
    public ResponseEntity<InvoicePaymentDTO> getInvoicePayment(@PathVariable Long id) {
        log.debug("REST request to get InvoicePayment : {}", id);
        Optional<InvoicePaymentDTO> invoicePaymentDTO = invoicePaymentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invoicePaymentDTO);
    }

    /**
     * {@code DELETE  /invoice-payments/:id} : delete the "id" invoicePayment.
     *
     * @param id the id of the invoicePaymentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invoice-payments/{id}")
    public ResponseEntity<Void> deleteInvoicePayment(@PathVariable Long id) {
        log.debug("REST request to delete InvoicePayment : {}", id);
        invoicePaymentService.delete(id);
        return ResponseEntity.noContent().headers(
            HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
