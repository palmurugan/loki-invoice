package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.service.ItemTypeService;
import com.way2invoice.bms.web.rest.errors.BadRequestAlertException;
import com.way2invoice.bms.service.dto.ItemTypeDTO;

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
 * REST controller for managing {@link com.way2invoice.bms.domain.ItemType}.
 */
@RestController
@RequestMapping("/api")
public class ItemTypeResource {

    private final Logger log = LoggerFactory.getLogger(ItemTypeResource.class);

    private static final String ENTITY_NAME = "itemType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemTypeService itemTypeService;

    public ItemTypeResource(ItemTypeService itemTypeService) {
        this.itemTypeService = itemTypeService;
    }

    /**
     * {@code POST  /item-types} : Create a new itemType.
     *
     * @param itemTypeDTO the itemTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemTypeDTO, or with status {@code 400 (Bad Request)} if the itemType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-types")
    public ResponseEntity<ItemTypeDTO> createItemType(@Valid @RequestBody ItemTypeDTO itemTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ItemType : {}", itemTypeDTO);
        if (itemTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemTypeDTO result = itemTypeService.save(itemTypeDTO);
        return ResponseEntity.created(new URI("/api/item-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-types} : Updates an existing itemType.
     *
     * @param itemTypeDTO the itemTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemTypeDTO,
     * or with status {@code 400 (Bad Request)} if the itemTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-types")
    public ResponseEntity<ItemTypeDTO> updateItemType(@Valid @RequestBody ItemTypeDTO itemTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ItemType : {}", itemTypeDTO);
        if (itemTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemTypeDTO result = itemTypeService.save(itemTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, itemTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /item-types} : get all the itemTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemTypes in body.
     */
    @GetMapping("/item-types")
    public ResponseEntity<List<ItemTypeDTO>> getAllItemTypes(Pageable pageable) {
        log.debug("REST request to get a page of ItemTypes");
        Page<ItemTypeDTO> page = itemTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-types/:id} : get the "id" itemType.
     *
     * @param id the id of the itemTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-types/{id}")
    public ResponseEntity<ItemTypeDTO> getItemType(@PathVariable Long id) {
        log.debug("REST request to get ItemType : {}", id);
        Optional<ItemTypeDTO> itemTypeDTO = itemTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemTypeDTO);
    }

    /**
     * {@code DELETE  /item-types/:id} : delete the "id" itemType.
     *
     * @param id the id of the itemTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-types/{id}")
    public ResponseEntity<Void> deleteItemType(@PathVariable Long id) {
        log.debug("REST request to delete ItemType : {}", id);
        itemTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
