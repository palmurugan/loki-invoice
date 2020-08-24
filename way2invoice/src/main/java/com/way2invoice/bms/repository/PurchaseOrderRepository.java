package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.PurchaseOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author palmuruganc
 * <p>
 * A spring data repository for the PurchaseOrder Entity
 */
@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

  Page<PurchaseOrder> findAllByStatus(String status, Pageable pageable);
}
