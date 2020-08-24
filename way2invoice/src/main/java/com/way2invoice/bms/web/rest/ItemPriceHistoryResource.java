package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.service.ItemPriceHistoryService;
import com.way2invoice.bms.web.rest.errors.BadRequestAlertException;
import com.way2invoice.bms.service.dto.ItemPriceHistoryDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.way2invoice.bms.domain.ItemPriceHistory}.
 */
@RestController
@RequestMapping("/api")
public class ItemPriceHistoryResource {

    private final Logger log = LoggerFactory.getLogger(ItemPriceHistoryResource.class);

    private static final String ENTITY_NAME = "itemPriceHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemPriceHistoryService itemPriceHistoryService;

    public ItemPriceHistoryResource(ItemPriceHistoryService itemPriceHistoryService) {
        this.itemPriceHistoryService = itemPriceHistoryService;
    }

    /**
     * {@code POST  /item-price-histories} : Create a new itemPriceHistory.
     *
     * @param itemPriceHistoryDTO the itemPriceHistoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemPriceHistoryDTO, or with status {@code 400 (Bad Request)} if the itemPriceHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-price-histories")
    public ResponseEntity<ItemPriceHistoryDTO> createItemPriceHistory(@Valid @RequestBody ItemPriceHistoryDTO itemPriceHistoryDTO) throws URISyntaxException {
        log.debug("REST request to save ItemPriceHistory : {}", itemPriceHistoryDTO);
        if (itemPriceHistoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemPriceHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemPriceHistoryDTO result = itemPriceHistoryService.save(itemPriceHistoryDTO);
        return ResponseEntity.created(new URI("/api/item-price-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-price-histories} : Updates an existing itemPriceHistory.
     *
     * @param itemPriceHistoryDTO the itemPriceHistoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemPriceHistoryDTO,
     * or with status {@code 400 (Bad Request)} if the itemPriceHistoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemPriceHistoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-price-histories")
    public ResponseEntity<ItemPriceHistoryDTO> updateItemPriceHistory(@Valid @RequestBody ItemPriceHistoryDTO itemPriceHistoryDTO) throws URISyntaxException {
        log.debug("REST request to update ItemPriceHistory : {}", itemPriceHistoryDTO);
        if (itemPriceHistoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemPriceHistoryDTO result = itemPriceHistoryService.save(itemPriceHistoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, itemPriceHistoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /item-price-histories} : get all the itemPriceHistories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemPriceHistories in body.
     */
    @GetMapping("/item-price-histories")
    public ResponseEntity<List<ItemPriceHistoryDTO>> getAllItemPriceHistories(Pageable pageable) {
        log.debug("REST request to get a page of ItemPriceHistories");
        Page<ItemPriceHistoryDTO> page = itemPriceHistoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-price-histories/:id} : get the "id" itemPriceHistory.
     *
     * @param id the id of the itemPriceHistoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemPriceHistoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-price-histories/{id}")
    public ResponseEntity<ItemPriceHistoryDTO> getItemPriceHistory(@PathVariable Long id) {
        log.debug("REST request to get ItemPriceHistory : {}", id);
        Optional<ItemPriceHistoryDTO> itemPriceHistoryDTO = itemPriceHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemPriceHistoryDTO);
    }

    /**
     * {@code DELETE  /item-price-histories/:id} : delete the "id" itemPriceHistory.
     *
     * @param id the id of the itemPriceHistoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-price-histories/{id}")
    public ResponseEntity<Void> deleteItemPriceHistory(@PathVariable Long id) {
        log.debug("REST request to delete ItemPriceHistory : {}", id);
        itemPriceHistoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
