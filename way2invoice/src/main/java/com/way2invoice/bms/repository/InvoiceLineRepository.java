package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.InvoiceLine;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InvoiceLine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoiceLineRepository extends JpaRepository<InvoiceLine, Long> {
}
