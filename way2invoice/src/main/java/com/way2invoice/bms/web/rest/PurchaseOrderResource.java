package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.service.PurchaseOrderService;
import com.way2invoice.bms.service.dto.PurchaseOrderDTO;
import com.way2invoice.bms.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author palmuruganc
 * <p>
 * REST controller for managing {@link com.way2invoice.bms.domain.PurchaseOrder}
 */
@RestController
@RequestMapping("/api/orders")
public class PurchaseOrderResource {

  private static final String ENTITY_NAME = "invoice";

  private final Logger log = LoggerFactory.getLogger(PurchaseOrderResource.class);
  private final PurchaseOrderService purchaseOrderService;

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  public PurchaseOrderResource(PurchaseOrderService purchaseOrderService) {
    this.purchaseOrderService = purchaseOrderService;
  }

  /**
   * {@code POST /orders} : create a new purchase order
   *
   * @param purchaseOrderDTO the input request
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} with body purchaseOrder or
   * {@code 400 (BadRequest)} if any validation failed
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping
  public ResponseEntity<PurchaseOrderDTO> createPurchaseOrder(
      @Valid @RequestBody PurchaseOrderDTO purchaseOrderDTO) throws URISyntaxException {
    log.debug("REST request to save purchase order: {}", purchaseOrderDTO);
    if (purchaseOrderDTO.getId() != null) {
      throw new BadRequestAlertException("A new puchase order cannot alreadt have an ID",
          ENTITY_NAME, "idExists");
    }
    PurchaseOrderDTO result = purchaseOrderService.save(purchaseOrderDTO);
    return ResponseEntity.created(new URI("/api/orders/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME,
            result.getId().toString())).body(result);
  }

  /**
   * {@code GET /orders} : get all purchase orders
   *
   * @param pageable the pagination information.
   * @return the {@link ResponseEntity} with the status {@code 200 (Ok)} and the list of purchase
   * orders in the body
   */
  @GetMapping
  public ResponseEntity<List<PurchaseOrderDTO>> getAllPurchaseOrders(Pageable pageable) {
    log.debug("REST request to page of purchase orders");
    Page<PurchaseOrderDTO> page = purchaseOrderService.findAll(pageable);
    HttpHeaders headers = PaginationUtil
        .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET /orders:id}: Get the purchase order based on the "id"
   *
   * @param id the purchase order id
   * @return the {@link ResponseEntity} with the status {@code 200 (Ok)} and the purchase order
   * details
   */
  @GetMapping("/{id}")
  public ResponseEntity<PurchaseOrderDTO> getPurchaseOrder(@PathVariable Long id) {
    log.debug("REST request to get purchase order: {}", id);
    return ResponseUtil.wrapOrNotFound(purchaseOrderService.findOne(id));
  }

}
