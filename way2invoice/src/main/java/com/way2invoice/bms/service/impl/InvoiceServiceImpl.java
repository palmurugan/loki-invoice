package com.way2invoice.bms.service.impl;

import static com.way2invoice.bms.util.ApplicationConstants.BILL_SEQ_PREFIX;
import static com.way2invoice.bms.util.ApplicationConstants.INVOICE_SEQ_PREFIX;
import static com.way2invoice.bms.util.ApplicationConstants.PURCHASE;
import static com.way2invoice.bms.util.ApplicationUtil.formatSequenceId;
import static com.way2invoice.bms.util.ApplicationUtil.roundUp;
import static com.way2invoice.bms.util.CalculationUtil.fnCalculateTax;
import static com.way2invoice.bms.util.CalculationUtil.fnDecimalMultiplication;

import com.way2invoice.bms.domain.Invoice;
import com.way2invoice.bms.domain.enumeration.InvoiceType;
import com.way2invoice.bms.domain.enumeration.SequenceType;
import com.way2invoice.bms.repository.InvoiceRepository;
import com.way2invoice.bms.service.InvoiceService;
import com.way2invoice.bms.service.SequenceService;
import com.way2invoice.bms.service.dto.InvoiceDTO;
import com.way2invoice.bms.service.dto.InvoiceLineDTO;
import com.way2invoice.bms.service.mapper.InvoiceMapper;
import java.math.BigDecimal;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Invoice}.
 */
@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private final Logger log = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    private final InvoiceRepository invoiceRepository;

    private final InvoiceMapper invoiceMapper;

    private final SequenceService sequenceService;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper,
        SequenceService sequenceService) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.sequenceService = sequenceService;
    }

    /**
     * Save a invoice.
     *
     * @param invoiceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InvoiceDTO save(InvoiceDTO invoiceDTO) {
        log.debug("Request to save Invoice : {}", invoiceDTO);

        /** This code block is used to identify the total value of the particular invoice */
        invoiceDTO.setTotal(roundUp(invoiceDTO.getInvoiceLines().stream()
            .map(this::calculateTotal).reduce(BigDecimal.valueOf(0), BigDecimal::add)));

        /** This section we are generating invoice/bill uniqueId with prefix */
        invoiceDTO.setName(getInvoiceName(invoiceDTO.getType()));

        Invoice invoice = invoiceMapper.toEntity(invoiceDTO);
        invoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDto(invoice);
    }

    private String getInvoiceName(InvoiceType type) {
        return type.equals(InvoiceType.S) ? formatSequenceId(INVOICE_SEQ_PREFIX,
            sequenceService.nextVal(SequenceType.INVOICE).toString())
            : formatSequenceId(BILL_SEQ_PREFIX,
                sequenceService.nextVal(SequenceType.BILL).toString());
    }

    /**
     * Get all the invoices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<InvoiceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Invoices");
        return invoiceRepository.findAll(pageable)
            .map(invoiceMapper::toDto);
    }

    /**
     * @param invoiceType
     * @param pageable    the pagination information
     * @return
     */
    @Override
    public Page<InvoiceDTO> findAll(String invoiceType,
        Pageable pageable) {
        log.debug("Request to get all invoice based on the type");
        InvoiceType invoiceTypeEnum = (invoiceType.equals(PURCHASE) ? InvoiceType.P
            : InvoiceType.S);
        return invoiceRepository.findByType(invoiceTypeEnum, pageable).map(invoiceMapper::toDto);
    }

    /**
     * Get one invoice by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InvoiceDTO> findOne(Long id) {
        log.debug("Request to get Invoice : {}", id);
        return invoiceRepository.findById(id)
            .map(invoiceMapper::toDto);
    }

    /**
     * Delete the invoice by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Invoice : {}", id);
        invoiceRepository.deleteById(id);
    }

    /**
     * Private method to calculate the total for each invoice line with Tax
     *
     * @param invoiceLineDTO
     * @return
     */
    private BigDecimal calculateTotal(InvoiceLineDTO invoiceLineDTO) {
        // TODO have to change the hardcoded tax
        BigDecimal amount = fnDecimalMultiplication
            .apply(invoiceLineDTO.getPrice(), BigDecimal.valueOf(invoiceLineDTO.getQuantity()));
        BigDecimal taxAmount = fnCalculateTax
            .apply(invoiceLineDTO.getPrice()
                    .multiply(BigDecimal.valueOf(invoiceLineDTO.getQuantity())),
                BigDecimal.valueOf(18));
        return amount.add(taxAmount);
    }
}
