package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.domain.State;
import com.way2invoice.bms.service.StateService;
import io.github.jhipster.web.util.HeaderUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A REST controller to manage state services
 */
@RestController
@RequestMapping("/api/states")
public class StateResource {

    private static final String ENTITY_NAME = "client";
    private final Logger log = LoggerFactory.getLogger(StateResource.class);
    private final StateService stateService;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public StateResource(StateService stateService) {
        this.stateService = stateService;
    }

    @PostMapping
    public ResponseEntity<State> createState(@RequestBody State state) throws URISyntaxException {
        log.debug("REST request to create State");
        State result = stateService.save(state);
        return ResponseEntity.created(new URI("/api/states/" + result.getId())).headers(HeaderUtil
            .createEntityCreationAlert(applicationName, true, ENTITY_NAME,
                result.getId().toString())).body(result);
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<List<State>> getAllStates(@PathVariable Long countryId) {
        log.debug("REST request to get all the states");
        return ResponseEntity.ok(stateService.findByCountryId(countryId));
    }
}
