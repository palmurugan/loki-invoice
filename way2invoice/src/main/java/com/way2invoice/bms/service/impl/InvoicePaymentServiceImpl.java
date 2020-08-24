package com.way2invoice.bms.service.impl;

import com.way2invoice.bms.domain.InvoicePayment;
import com.way2invoice.bms.domain.enumeration.InvoiceType;
import com.way2invoice.bms.repository.InvoicePaymentRepository;
import com.way2invoice.bms.service.InvoicePaymentService;
import com.way2invoice.bms.service.dto.InvoicePaymentDTO;
import com.way2invoice.bms.service.mapper.InvoicePaymentMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link InvoicePayment}.
 */
@Service
@Transactional
public class InvoicePaymentServiceImpl implements InvoicePaymentService {

    private final Logger log = LoggerFactory.getLogger(InvoicePaymentServiceImpl.class);

    private final InvoicePaymentRepository invoicePaymentRepository;

    private final InvoicePaymentMapper invoicePaymentMapper;

    public InvoicePaymentServiceImpl(InvoicePaymentRepository invoicePaymentRepository,
        InvoicePaymentMapper invoicePaymentMapper) {
        this.invoicePaymentRepository = invoicePaymentRepository;
        this.invoicePaymentMapper = invoicePaymentMapper;
    }

    /**
     * Save a invoicePayment.
     *
     * @param invoicePaymentDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InvoicePaymentDTO save(InvoicePaymentDTO invoicePaymentDTO) {
        log.debug("Request to save InvoicePayment : {}", invoicePaymentDTO);
        InvoicePayment invoicePayment = invoicePaymentMapper.toEntity(invoicePaymentDTO);
        invoicePayment = invoicePaymentRepository.save(invoicePayment);
        return invoicePaymentMapper.toDto(invoicePayment);
    }

    /**
     * Get all the invoicePayments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<InvoicePaymentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InvoicePayments");
        return invoicePaymentRepository.findAll(pageable)
            .map(invoicePaymentMapper::toDto);
    }

    /**
     * Get all the invoice payment based on the type
     *
     * @param invoiceType the input parameter
     * @param pageable    the pagination information
     * @return
     */
    @Override
    public Page<InvoicePaymentDTO> findAll(InvoiceType invoiceType, Pageable pageable) {
        log.debug("Request to find all invoice payments by type {}", invoiceType);
        return invoicePaymentRepository.findByInvoiceType(invoiceType, pageable)
            .map(invoicePaymentMapper::toDto);
    }

    /**
     * Get one invoicePayment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InvoicePaymentDTO> findOne(Long id) {
        log.debug("Request to get InvoicePayment : {}", id);
        return invoicePaymentRepository.findById(id)
            .map(invoicePaymentMapper::toDto);
    }

    /**
     * Delete the invoicePayment by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete InvoicePayment : {}", id);
        invoicePaymentRepository.deleteById(id);
    }
}
