package com.way2invoice.bms.service;

import com.way2invoice.bms.domain.enumeration.InvoiceType;
import com.way2invoice.bms.service.dto.InvoicePaymentDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.way2invoice.bms.domain.InvoicePayment}.
 */
public interface InvoicePaymentService {

    /**
     * Save a invoicePayment.
     *
     * @param invoicePaymentDTO the entity to save.
     * @return the persisted entity.
     */
    InvoicePaymentDTO save(InvoicePaymentDTO invoicePaymentDTO);

    /**
     * Get all the invoicePayments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InvoicePaymentDTO> findAll(Pageable pageable);

    /**
     * Gat all the invoice payment by using the types
     *
     * @param invoiceType the input parameter
     * @param pageable    the pagination information
     * @return the list of invoice payment details
     */
    Page<InvoicePaymentDTO> findAll(InvoiceType invoiceType, Pageable pageable);


    /**
     * Get the "id" invoicePayment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InvoicePaymentDTO> findOne(Long id);

    /**
     * Delete the "id" invoicePayment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
