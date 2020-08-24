package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.Invoice;
import com.way2invoice.bms.domain.enumeration.InvoiceType;
import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Invoice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Page<Invoice> findByType(InvoiceType invoiceType, Pageable pageable);

    @Query("Select sum(i.total) from Invoice i where i.type = :invoiceType")
    BigDecimal findTotalAmount(@Param("invoiceType") InvoiceType invoiceType);
}
