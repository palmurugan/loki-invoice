package com.way2invoice.bms.service;

import com.way2invoice.bms.service.dto.InvoiceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.way2invoice.bms.domain.Invoice}.
 */
public interface InvoiceService {

    /**
     * Save a invoice.
     *
     * @param invoiceDTO the entity to save.
     * @return the persisted entity.
     */
    InvoiceDTO save(InvoiceDTO invoiceDTO);

    /**
     * Get all the invoices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InvoiceDTO> findAll(Pageable pageable);

    /**
     * Get all the invoice based on the type
     *
     * @param invoiceType
     * @param pageable the pagination information
     * @return the list of invoices based on the type
     */
    Page<InvoiceDTO> findAll(String invoiceType, Pageable pageable);


    /**
     * Get the "id" invoice.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InvoiceDTO> findOne(Long id);

    /**
     * Delete the "id" invoice.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
