package com.way2invoice.bms.service.impl;

import com.way2invoice.bms.domain.PurchaseOrder;
import com.way2invoice.bms.repository.PurchaseOrderRepository;
import com.way2invoice.bms.service.PurchaseOrderService;
import com.way2invoice.bms.service.dto.PurchaseOrderDTO;
import com.way2invoice.bms.service.mapper.PurchaseOrderMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author palmuruganc
 * <p>
 * A service implementation for managing {@link com.way2invoice.bms.domain.PurchaseOrder}
 */
@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

  private final Logger log = LoggerFactory.getLogger(PurchaseOrderServiceImpl.class);

  private final PurchaseOrderRepository purchaseOrderRepository;

  private final PurchaseOrderMapper purchaseOrderMapper;

  public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository,
      PurchaseOrderMapper purchaseOrderMapper) {
    this.purchaseOrderRepository = purchaseOrderRepository;
    this.purchaseOrderMapper = purchaseOrderMapper;
  }

  /**
   * Save a purchase order
   *
   * @param purchaseOrderDTO the entity to save
   * @return the persisted purchase order
   */
  @Override
  public PurchaseOrderDTO save(PurchaseOrderDTO purchaseOrderDTO) {
    log.debug("Request to save purchase order: {}", purchaseOrderDTO);
    PurchaseOrder purchaseOrder = purchaseOrderMapper.toEntity(purchaseOrderDTO);
    return purchaseOrderMapper
        .toDto(purchaseOrderRepository.save(purchaseOrder));
  }

  /**
   * Retrieve all the purchase order
   *
   * @param pageable the paging information
   * @return purchase order list with paging information
   */
  @Override
  public Page<PurchaseOrderDTO> findAll(Pageable pageable) {
    log.debug("Request to retrieve all Purchase orders");
    return purchaseOrderRepository.findAll(pageable).map(purchaseOrderMapper::toDto);
  }

  /**
   * Get the purchase order
   *
   * @param id the purchase order id
   * @return the purchase order
   */
  @Override
  public Optional<PurchaseOrderDTO> findOne(Long id) {
    log.debug("Request to get the purchase order: {}", id);
    return purchaseOrderRepository.findById(id).map(purchaseOrderMapper::toDto);
  }
}
