package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.service.AccountTaxInfoService;
import com.way2invoice.bms.web.rest.errors.BadRequestAlertException;
import com.way2invoice.bms.service.dto.AccountTaxInfoDTO;

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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.way2invoice.bms.domain.AccountTaxInfo}.
 */
@RestController
@RequestMapping("/api")
public class AccountTaxInfoResource {

    private final Logger log = LoggerFactory.getLogger(AccountTaxInfoResource.class);

    private static final String ENTITY_NAME = "accountTaxInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccountTaxInfoService accountTaxInfoService;

    public AccountTaxInfoResource(AccountTaxInfoService accountTaxInfoService) {
        this.accountTaxInfoService = accountTaxInfoService;
    }

    /**
     * {@code POST  /account-tax-infos} : Create a new accountTaxInfo.
     *
     * @param accountTaxInfoDTO the accountTaxInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accountTaxInfoDTO, or with status {@code 400 (Bad Request)} if the accountTaxInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/account-tax-infos")
    public ResponseEntity<AccountTaxInfoDTO> createAccountTaxInfo(@RequestBody AccountTaxInfoDTO accountTaxInfoDTO) throws URISyntaxException {
        log.debug("REST request to save AccountTaxInfo : {}", accountTaxInfoDTO);
        if (accountTaxInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new accountTaxInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccountTaxInfoDTO result = accountTaxInfoService.save(accountTaxInfoDTO);
        return ResponseEntity.created(new URI("/api/account-tax-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /account-tax-infos} : Updates an existing accountTaxInfo.
     *
     * @param accountTaxInfoDTO the accountTaxInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountTaxInfoDTO,
     * or with status {@code 400 (Bad Request)} if the accountTaxInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accountTaxInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/account-tax-infos")
    public ResponseEntity<AccountTaxInfoDTO> updateAccountTaxInfo(@RequestBody AccountTaxInfoDTO accountTaxInfoDTO) throws URISyntaxException {
        log.debug("REST request to update AccountTaxInfo : {}", accountTaxInfoDTO);
        if (accountTaxInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccountTaxInfoDTO result = accountTaxInfoService.save(accountTaxInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, accountTaxInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /account-tax-infos} : get all the accountTaxInfos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accountTaxInfos in body.
     */
    @GetMapping("/account-tax-infos")
    public ResponseEntity<List<AccountTaxInfoDTO>> getAllAccountTaxInfos(Pageable pageable) {
        log.debug("REST request to get a page of AccountTaxInfos");
        Page<AccountTaxInfoDTO> page = accountTaxInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /account-tax-infos/:id} : get the "id" accountTaxInfo.
     *
     * @param id the id of the accountTaxInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accountTaxInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/account-tax-infos/{id}")
    public ResponseEntity<AccountTaxInfoDTO> getAccountTaxInfo(@PathVariable Long id) {
        log.debug("REST request to get AccountTaxInfo : {}", id);
        Optional<AccountTaxInfoDTO> accountTaxInfoDTO = accountTaxInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accountTaxInfoDTO);
    }

    /**
     * {@code DELETE  /account-tax-infos/:id} : delete the "id" accountTaxInfo.
     *
     * @param id the id of the accountTaxInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/account-tax-infos/{id}")
    public ResponseEntity<Void> deleteAccountTaxInfo(@PathVariable Long id) {
        log.debug("REST request to delete AccountTaxInfo : {}", id);

        accountTaxInfoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
