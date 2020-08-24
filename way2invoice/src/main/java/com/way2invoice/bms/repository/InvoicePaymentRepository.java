package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.InvoicePayment;

import com.way2invoice.bms.domain.enumeration.InvoiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InvoicePayment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoicePaymentRepository extends JpaRepository<InvoicePayment, Long> {

    Page<InvoicePayment> findByInvoiceType(InvoiceType invoiceType, Pageable pageable);
}
