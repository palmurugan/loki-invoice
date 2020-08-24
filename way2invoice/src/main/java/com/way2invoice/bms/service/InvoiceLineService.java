package com.way2invoice.bms.service;

import com.way2invoice.bms.service.dto.InvoiceLineDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.way2invoice.bms.domain.InvoiceLine}.
 */
public interface InvoiceLineService {

    /**
     * Save a invoiceLine.
     *
     * @param invoiceLineDTO the entity to save.
     * @return the persisted entity.
     */
    InvoiceLineDTO save(InvoiceLineDTO invoiceLineDTO);

    /**
     * Get all the invoiceLines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InvoiceLineDTO> findAll(Pageable pageable);


    /**
     * Get the "id" invoiceLine.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InvoiceLineDTO> findOne(Long id);

    /**
     * Delete the "id" invoiceLine.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
