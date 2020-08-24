package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.domain.Country;
import com.way2invoice.bms.service.CountryService;
import com.way2invoice.bms.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing the domain {@link com.way2invoice.bms.domain.Country}
 */
@RestController
@RequestMapping("/api/countries")
public class CountryResource {

    private static final String ENTITY_NAME = "client";
    private final Logger log = LoggerFactory.getLogger(CountryResource.class);
    private final CountryService countryService;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public CountryResource(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public ResponseEntity<Country> createCountry(@RequestBody Country country)
        throws URISyntaxException {
        log.debug("REST request to create country {}", country);
        if (country.getId() != null) {
            throw new BadRequestAlertException("A new country cannot already have an ID",
                ENTITY_NAME, "idexists");
        }
        Country result = countryService.save(country);
        return ResponseEntity.created(new URI("/api/countries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME,
                result.getId().toString()))
            .body(result);
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        log.debug("REST request to get all countries");
        return ResponseEntity.ok(countryService.findAll());
    }
}
