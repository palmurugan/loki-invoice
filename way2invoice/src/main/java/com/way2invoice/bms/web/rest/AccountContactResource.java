package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.service.AccountContactService;
import com.way2invoice.bms.service.dto.AccountContactDTO;
import com.way2invoice.bms.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link com.way2invoice.bms.domain.AccountContact}.
 */
@RestController
@RequestMapping("/api")
public class AccountContactResource {

    private static final String ENTITY_NAME = "accountContact";
    private final Logger log = LoggerFactory.getLogger(AccountContactResource.class);
    private final AccountContactService accountContactService;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public AccountContactResource(AccountContactService accountContactService) {
        this.accountContactService = accountContactService;
    }

    /**
     * {@code POST  /account-contacts} : Create a new accountContact.
     *
     * @param accountContactDTO the accountContactDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new
     * accountContactDTO, or with status {@code 400 (Bad Request)} if the accountContact has already
     * an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/account-contacts")
    public ResponseEntity<AccountContactDTO> createAccountContact(
        @Valid @RequestBody AccountContactDTO accountContactDTO) throws URISyntaxException {
        log.debug("REST request to save AccountContact : {}", accountContactDTO);
        if (accountContactDTO.getId() != null) {
            throw new BadRequestAlertException("A new accountContact cannot already have an ID",
                ENTITY_NAME, "idexists");
        }
        AccountContactDTO result = accountContactService.save(accountContactDTO);
        return ResponseEntity.created(new URI("/api/account-contacts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME,
                result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /account-contacts} : Updates an existing accountContact.
     *
     * @param accountContactDTO the accountContactDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated
     * accountContactDTO, or with status {@code 400 (Bad Request)} if the accountContactDTO is not
     * valid, or with status {@code 500 (Internal Server Error)} if the accountContactDTO couldn't
     * be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/account-contacts")
    public ResponseEntity<AccountContactDTO> updateAccountContact(
        @Valid @RequestBody AccountContactDTO accountContactDTO) throws URISyntaxException {
        log.debug("REST request to update AccountContact : {}", accountContactDTO);
        if (accountContactDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccountContactDTO result = accountContactService.save(accountContactDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                accountContactDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /account-contacts} : get all the accountContacts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of
     * accountContacts in body.
     */
    @GetMapping("/account-contacts")
    public ResponseEntity<List<AccountContactDTO>> getAllAccountContacts(Pageable pageable) {
        log.debug("REST request to get a page of AccountContacts");
        Page<AccountContactDTO> page = accountContactService.findAll(pageable);
        HttpHeaders headers = PaginationUtil
            .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /account-contacts/:id} : get the "id" accountContact.
     *
     * @param id the id of the accountContactDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the
     * accountContactDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/account-contacts/{id}")
    public ResponseEntity<AccountContactDTO> getAccountContact(@PathVariable Long id) {
        log.debug("REST request to get AccountContact : {}", id);
        Optional<AccountContactDTO> accountContactDTO = accountContactService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accountContactDTO);
    }

    /**
     * {@code DELETE  /account-contacts/:id} : delete the "id" accountContact.
     *
     * @param id the id of the accountContactDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/account-contacts/{id}")
    public ResponseEntity<Void> deleteAccountContact(@PathVariable Long id) {
        log.debug("REST request to delete AccountContact : {}", id);

        accountContactService.delete(id);
        return ResponseEntity.noContent().headers(
            HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
