package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.service.AccountGroupService;
import com.way2invoice.bms.web.rest.errors.BadRequestAlertException;
import com.way2invoice.bms.service.dto.AccountGroupDTO;

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
 * REST controller for managing {@link com.way2invoice.bms.domain.AccountGroup}.
 */
@RestController
@RequestMapping("/api")
public class AccountGroupResource {

    private final Logger log = LoggerFactory.getLogger(AccountGroupResource.class);

    private static final String ENTITY_NAME = "accountGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccountGroupService accountGroupService;

    public AccountGroupResource(AccountGroupService accountGroupService) {
        this.accountGroupService = accountGroupService;
    }

    /**
     * {@code POST  /account-groups} : Create a new accountGroup.
     *
     * @param accountGroupDTO the accountGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accountGroupDTO, or with status {@code 400 (Bad Request)} if the accountGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/account-groups")
    public ResponseEntity<AccountGroupDTO> createAccountGroup(@Valid @RequestBody AccountGroupDTO accountGroupDTO) throws URISyntaxException {
        log.debug("REST request to save AccountGroup : {}", accountGroupDTO);
        if (accountGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new accountGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccountGroupDTO result = accountGroupService.save(accountGroupDTO);
        return ResponseEntity.created(new URI("/api/account-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /account-groups} : Updates an existing accountGroup.
     *
     * @param accountGroupDTO the accountGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountGroupDTO,
     * or with status {@code 400 (Bad Request)} if the accountGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accountGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/account-groups")
    public ResponseEntity<AccountGroupDTO> updateAccountGroup(@Valid @RequestBody AccountGroupDTO accountGroupDTO) throws URISyntaxException {
        log.debug("REST request to update AccountGroup : {}", accountGroupDTO);
        if (accountGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccountGroupDTO result = accountGroupService.save(accountGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, accountGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /account-groups} : get all the accountGroups.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accountGroups in body.
     */
    @GetMapping("/account-groups")
    public ResponseEntity<List<AccountGroupDTO>> getAllAccountGroups(Pageable pageable) {
        log.debug("REST request to get a page of AccountGroups");
        Page<AccountGroupDTO> page = accountGroupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /account-groups/:id} : get the "id" accountGroup.
     *
     * @param id the id of the accountGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accountGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/account-groups/{id}")
    public ResponseEntity<AccountGroupDTO> getAccountGroup(@PathVariable Long id) {
        log.debug("REST request to get AccountGroup : {}", id);
        Optional<AccountGroupDTO> accountGroupDTO = accountGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accountGroupDTO);
    }

    /**
     * {@code DELETE  /account-groups/:id} : delete the "id" accountGroup.
     *
     * @param id the id of the accountGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/account-groups/{id}")
    public ResponseEntity<Void> deleteAccountGroup(@PathVariable Long id) {
        log.debug("REST request to delete AccountGroup : {}", id);

        accountGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
