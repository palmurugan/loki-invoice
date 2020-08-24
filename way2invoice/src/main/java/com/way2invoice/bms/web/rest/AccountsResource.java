package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.common.response.WidgetData;
import com.way2invoice.bms.service.AccountsService;
import com.way2invoice.bms.service.dto.AccountContactDTO;
import com.way2invoice.bms.service.dto.AccountsDTO;
import com.way2invoice.bms.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link com.way2invoice.bms.domain.Accounts}.
 */
@RestController
@RequestMapping("/api")
public class AccountsResource {

    private static final String ENTITY_NAME = "accounts";
    private final Logger log = LoggerFactory.getLogger(AccountsResource.class);
    private final AccountsService accountsService;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public AccountsResource(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    /**
     * {@code POST  /accounts} : Create a new accounts.
     *
     * @param accountsDTO the accountsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new
     * accountsDTO, or with status {@code 400 (Bad Request)} if the accounts has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/accounts")
    public ResponseEntity<AccountsDTO> createAccounts(@Valid @RequestBody AccountsDTO accountsDTO)
        throws URISyntaxException {
        log.debug("REST request to save Accounts : {}", accountsDTO);
        if (accountsDTO.getId() != null) {
            throw new BadRequestAlertException("A new accounts cannot already have an ID",
                ENTITY_NAME, "idexists");
        }
        AccountsDTO result = accountsService.save(accountsDTO);
        return ResponseEntity.created(new URI("/api/accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME,
                result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /accounts} : Updates an existing accounts.
     *
     * @param accountsDTO the accountsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated
     * accountsDTO, or with status {@code 400 (Bad Request)} if the accountsDTO is not valid, or
     * with status {@code 500 (Internal Server Error)} if the accountsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/accounts")
    public ResponseEntity<AccountsDTO> updateAccounts(@Valid @RequestBody AccountsDTO accountsDTO)
        throws URISyntaxException {
        log.debug("REST request to update Accounts : {}", accountsDTO);
        if (accountsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccountsDTO result = accountsService.save(accountsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                accountsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /accounts} : get all the accounts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accounts in
     * body.
     */
    @GetMapping("/accounts")
    public ResponseEntity<List<AccountsDTO>> getAllAccounts(Pageable pageable,
        @RequestParam(required = false) String accountType) {
        log.debug("REST request to get a page of Accounts");
        Page<AccountsDTO> page =
            StringUtils.isNotEmpty(accountType) ? accountsService.findAll(accountType, pageable)
                : accountsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil
            .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /accounts/:id} : get the "id" accounts.
     *
     * @param id the id of the accountsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the
     * accountsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountsDTO> getAccounts(@PathVariable Long id) {
        log.debug("REST request to get Accounts : {}", id);
        Optional<AccountsDTO> accountsDTO = accountsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accountsDTO);
    }

    /**
     * {@code GET /accounts/{id}/contact} : get the contact of the account
     *
     * @param id the account id
     * @return the contact details (Only primary contact)
     */
    @GetMapping("/accounts/{id}/contact")
    public ResponseEntity<AccountContactDTO> getAccountContact(@PathVariable Long id) {
        log.debug("REST request to get the contact detail of the account {}", id);
        return ResponseUtil.wrapOrNotFound(accountsService.findPrimaryContact(id));
    }

    /**
     * {@code /accounts/{id}/tax/type} : find the tax type
     *
     * @param id the account Id
     * @return the {@link WidgetData} which contains the tax type.
     */
    @GetMapping("/accounts/{id}/tax/types")
    public ResponseEntity<WidgetData> findTaxType(@PathVariable Long id) {
        log.debug("REST request to find the tax type for the account {}", id);
        return ResponseUtil.wrapOrNotFound(accountsService.findTaxType(id));
    }

    /**
     * {@code DELETE  /accounts/:id} : delete the "id" accounts.
     *
     * @param id the id of the accountsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Void> deleteAccounts(@PathVariable Long id) {
        log.debug("REST request to delete Accounts : {}", id);

        accountsService.delete(id);
        return ResponseEntity.noContent().headers(
            HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
