package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.domain.Company;
import com.way2invoice.bms.service.CompanyService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A REST controller to manage the domain {@link com.way2invoice.bms.domain.Company}
 */
@RestController
@RequestMapping("/api/companies")
public class CompanyResource {

    private static final Logger log = LoggerFactory.getLogger(CompanyResource.class);
    private final CompanyService companyService;

    public CompanyResource(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id) {
        log.debug("REST request to get the company details for the id {}", id);
        return ResponseUtil.wrapOrNotFound(companyService.findById(id));
    }
}
