package com.way2invoice.bms.service;

import com.way2invoice.bms.service.dto.PurchaseOrderDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for managing {@link com.way2invoice.bms.domain.PurchaseOrder}
 */
public interface PurchaseOrderService {

  /**
   * Save a purchase order
   *
   * @param purchaseOrderDTO the entity to save
   * @return the persisted entity
   */
  PurchaseOrderDTO save(PurchaseOrderDTO purchaseOrderDTO);

  /**
   * Get all the purchase orders
   *
   * @param pageable the paging information
   * @return the list of entities
   */
  Page<PurchaseOrderDTO> findAll(Pageable pageable);

  /**
   * Get the purchase order
   *
   * @param id the purchase order id
   * @return the purchser order
   */
  Optional<PurchaseOrderDTO> findOne(Long id);
}
