package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.service.ItemPriceService;
import com.way2invoice.bms.web.rest.errors.BadRequestAlertException;
import com.way2invoice.bms.service.dto.ItemPriceDTO;

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
 * REST controller for managing {@link com.way2invoice.bms.domain.ItemPrice}.
 */
@RestController
@RequestMapping("/api")
public class ItemPriceResource {

    private final Logger log = LoggerFactory.getLogger(ItemPriceResource.class);

    private static final String ENTITY_NAME = "itemPrice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemPriceService itemPriceService;

    public ItemPriceResource(ItemPriceService itemPriceService) {
        this.itemPriceService = itemPriceService;
    }

    /**
     * {@code POST  /item-prices} : Create a new itemPrice.
     *
     * @param itemPriceDTO the itemPriceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemPriceDTO, or with status {@code 400 (Bad Request)} if the itemPrice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-prices")
    public ResponseEntity<ItemPriceDTO> createItemPrice(@Valid @RequestBody ItemPriceDTO itemPriceDTO) throws URISyntaxException {
        log.debug("REST request to save ItemPrice : {}", itemPriceDTO);
        if (itemPriceDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemPrice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemPriceDTO result = itemPriceService.save(itemPriceDTO);
        return ResponseEntity.created(new URI("/api/item-prices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-prices} : Updates an existing itemPrice.
     *
     * @param itemPriceDTO the itemPriceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemPriceDTO,
     * or with status {@code 400 (Bad Request)} if the itemPriceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemPriceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-prices")
    public ResponseEntity<ItemPriceDTO> updateItemPrice(@Valid @RequestBody ItemPriceDTO itemPriceDTO) throws URISyntaxException {
        log.debug("REST request to update ItemPrice : {}", itemPriceDTO);
        if (itemPriceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemPriceDTO result = itemPriceService.save(itemPriceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, itemPriceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /item-prices} : get all the itemPrices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemPrices in body.
     */
    @GetMapping("/item-prices")
    public ResponseEntity<List<ItemPriceDTO>> getAllItemPrices(Pageable pageable) {
        log.debug("REST request to get a page of ItemPrices");
        Page<ItemPriceDTO> page = itemPriceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-prices/:id} : get the "id" itemPrice.
     *
     * @param id the id of the itemPriceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemPriceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-prices/{id}")
    public ResponseEntity<ItemPriceDTO> getItemPrice(@PathVariable Long id) {
        log.debug("REST request to get ItemPrice : {}", id);
        Optional<ItemPriceDTO> itemPriceDTO = itemPriceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemPriceDTO);
    }

    /**
     * {@code DELETE  /item-prices/:id} : delete the "id" itemPrice.
     *
     * @param id the id of the itemPriceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-prices/{id}")
    public ResponseEntity<Void> deleteItemPrice(@PathVariable Long id) {
        log.debug("REST request to delete ItemPrice : {}", id);
        itemPriceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
