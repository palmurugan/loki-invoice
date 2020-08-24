package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.service.InvoiceLineService;
import com.way2invoice.bms.web.rest.errors.BadRequestAlertException;
import com.way2invoice.bms.service.dto.InvoiceLineDTO;

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
 * REST controller for managing {@link com.way2invoice.bms.domain.InvoiceLine}.
 */
@RestController
@RequestMapping("/api")
public class InvoiceLineResource {

    private final Logger log = LoggerFactory.getLogger(InvoiceLineResource.class);

    private static final String ENTITY_NAME = "invoiceLine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvoiceLineService invoiceLineService;

    public InvoiceLineResource(InvoiceLineService invoiceLineService) {
        this.invoiceLineService = invoiceLineService;
    }

    /**
     * {@code POST  /invoice-lines} : Create a new invoiceLine.
     *
     * @param invoiceLineDTO the invoiceLineDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invoiceLineDTO, or with status {@code 400 (Bad Request)} if the invoiceLine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invoice-lines")
    public ResponseEntity<InvoiceLineDTO> createInvoiceLine(@Valid @RequestBody InvoiceLineDTO invoiceLineDTO) throws URISyntaxException {
        log.debug("REST request to save InvoiceLine : {}", invoiceLineDTO);
        if (invoiceLineDTO.getId() != null) {
            throw new BadRequestAlertException("A new invoiceLine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvoiceLineDTO result = invoiceLineService.save(invoiceLineDTO);
        return ResponseEntity.created(new URI("/api/invoice-lines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /invoice-lines} : Updates an existing invoiceLine.
     *
     * @param invoiceLineDTO the invoiceLineDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceLineDTO,
     * or with status {@code 400 (Bad Request)} if the invoiceLineDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invoiceLineDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invoice-lines")
    public ResponseEntity<InvoiceLineDTO> updateInvoiceLine(@Valid @RequestBody InvoiceLineDTO invoiceLineDTO) throws URISyntaxException {
        log.debug("REST request to update InvoiceLine : {}", invoiceLineDTO);
        if (invoiceLineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvoiceLineDTO result = invoiceLineService.save(invoiceLineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, invoiceLineDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /invoice-lines} : get all the invoiceLines.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invoiceLines in body.
     */
    @GetMapping("/invoice-lines")
    public ResponseEntity<List<InvoiceLineDTO>> getAllInvoiceLines(Pageable pageable) {
        log.debug("REST request to get a page of InvoiceLines");
        Page<InvoiceLineDTO> page = invoiceLineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /invoice-lines/:id} : get the "id" invoiceLine.
     *
     * @param id the id of the invoiceLineDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invoiceLineDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invoice-lines/{id}")
    public ResponseEntity<InvoiceLineDTO> getInvoiceLine(@PathVariable Long id) {
        log.debug("REST request to get InvoiceLine : {}", id);
        Optional<InvoiceLineDTO> invoiceLineDTO = invoiceLineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invoiceLineDTO);
    }

    /**
     * {@code DELETE  /invoice-lines/:id} : delete the "id" invoiceLine.
     *
     * @param id the id of the invoiceLineDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invoice-lines/{id}")
    public ResponseEntity<Void> deleteInvoiceLine(@PathVariable Long id) {
        log.debug("REST request to delete InvoiceLine : {}", id);
        invoiceLineService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
