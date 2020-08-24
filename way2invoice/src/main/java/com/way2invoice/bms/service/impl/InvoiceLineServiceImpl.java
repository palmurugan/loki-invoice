package com.way2invoice.bms.service.impl;

import com.way2invoice.bms.service.InvoiceLineService;
import com.way2invoice.bms.domain.InvoiceLine;
import com.way2invoice.bms.repository.InvoiceLineRepository;
import com.way2invoice.bms.service.dto.InvoiceLineDTO;
import com.way2invoice.bms.service.mapper.InvoiceLineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link InvoiceLine}.
 */
@Service
@Transactional
public class InvoiceLineServiceImpl implements InvoiceLineService {

    private final Logger log = LoggerFactory.getLogger(InvoiceLineServiceImpl.class);

    private final InvoiceLineRepository invoiceLineRepository;

    private final InvoiceLineMapper invoiceLineMapper;

    public InvoiceLineServiceImpl(InvoiceLineRepository invoiceLineRepository, InvoiceLineMapper invoiceLineMapper) {
        this.invoiceLineRepository = invoiceLineRepository;
        this.invoiceLineMapper = invoiceLineMapper;
    }

    /**
     * Save a invoiceLine.
     *
     * @param invoiceLineDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InvoiceLineDTO save(InvoiceLineDTO invoiceLineDTO) {
        log.debug("Request to save InvoiceLine : {}", invoiceLineDTO);
        InvoiceLine invoiceLine = invoiceLineMapper.toEntity(invoiceLineDTO);
        invoiceLine = invoiceLineRepository.save(invoiceLine);
        return invoiceLineMapper.toDto(invoiceLine);
    }

    /**
     * Get all the invoiceLines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<InvoiceLineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InvoiceLines");
        return invoiceLineRepository.findAll(pageable)
            .map(invoiceLineMapper::toDto);
    }


    /**
     * Get one invoiceLine by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InvoiceLineDTO> findOne(Long id) {
        log.debug("Request to get InvoiceLine : {}", id);
        return invoiceLineRepository.findById(id)
            .map(invoiceLineMapper::toDto);
    }

    /**
     * Delete the invoiceLine by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete InvoiceLine : {}", id);
        invoiceLineRepository.deleteById(id);
    }
}
