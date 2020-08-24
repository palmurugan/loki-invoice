package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.Way2InvoiceApp;
import com.way2invoice.bms.domain.Tax;
import com.way2invoice.bms.repository.TaxRepository;
import com.way2invoice.bms.service.TaxService;
import com.way2invoice.bms.service.dto.TaxDTO;
import com.way2invoice.bms.service.mapper.TaxMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.way2invoice.bms.domain.enumeration.Status;
/**
 * Integration tests for the {@link TaxResource} REST controller.
 */
@SpringBootTest(classes = Way2InvoiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TaxResourceIT {

    private static final Long DEFAULT_CLIENT_ID = 1L;
    private static final Long UPDATED_CLIENT_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.DEACTIVE;

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    private TaxMapper taxMapper;

    @Autowired
    private TaxService taxService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaxMockMvc;

    private Tax tax;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tax createEntity(EntityManager em) {
        Tax tax = new Tax()
            .clientId(DEFAULT_CLIENT_ID)
            .name(DEFAULT_NAME)
            .code(DEFAULT_CODE)
            .status(DEFAULT_STATUS);
        return tax;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tax createUpdatedEntity(EntityManager em) {
        Tax tax = new Tax()
            .clientId(UPDATED_CLIENT_ID)
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .status(UPDATED_STATUS);
        return tax;
    }

    @BeforeEach
    public void initTest() {
        tax = createEntity(em);
    }

    @Test
    @Transactional
    public void createTax() throws Exception {
        int databaseSizeBeforeCreate = taxRepository.findAll().size();
        // Create the Tax
        TaxDTO taxDTO = taxMapper.toDto(tax);
        restTaxMockMvc.perform(post("/api/taxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxDTO)))
            .andExpect(status().isCreated());

        // Validate the Tax in the database
        List<Tax> taxList = taxRepository.findAll();
        assertThat(taxList).hasSize(databaseSizeBeforeCreate + 1);
        Tax testTax = taxList.get(taxList.size() - 1);
        assertThat(testTax.getClientId()).isEqualTo(DEFAULT_CLIENT_ID);
        assertThat(testTax.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTax.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTax.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createTaxWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = taxRepository.findAll().size();

        // Create the Tax with an existing ID
        tax.setId(1L);
        TaxDTO taxDTO = taxMapper.toDto(tax);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaxMockMvc.perform(post("/api/taxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tax in the database
        List<Tax> taxList = taxRepository.findAll();
        assertThat(taxList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkClientIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = taxRepository.findAll().size();
        // set the field null
        tax.setClientId(null);

        // Create the Tax, which fails.
        TaxDTO taxDTO = taxMapper.toDto(tax);


        restTaxMockMvc.perform(post("/api/taxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxDTO)))
            .andExpect(status().isBadRequest());

        List<Tax> taxList = taxRepository.findAll();
        assertThat(taxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = taxRepository.findAll().size();
        // set the field null
        tax.setName(null);

        // Create the Tax, which fails.
        TaxDTO taxDTO = taxMapper.toDto(tax);


        restTaxMockMvc.perform(post("/api/taxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxDTO)))
            .andExpect(status().isBadRequest());

        List<Tax> taxList = taxRepository.findAll();
        assertThat(taxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = taxRepository.findAll().size();
        // set the field null
        tax.setCode(null);

        // Create the Tax, which fails.
        TaxDTO taxDTO = taxMapper.toDto(tax);


        restTaxMockMvc.perform(post("/api/taxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxDTO)))
            .andExpect(status().isBadRequest());

        List<Tax> taxList = taxRepository.findAll();
        assertThat(taxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTaxes() throws Exception {
        // Initialize the database
        taxRepository.saveAndFlush(tax);

        // Get all the taxList
        restTaxMockMvc.perform(get("/api/taxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tax.getId().intValue())))
            .andExpect(jsonPath("$.[*].clientId").value(hasItem(DEFAULT_CLIENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getTax() throws Exception {
        // Initialize the database
        taxRepository.saveAndFlush(tax);

        // Get the tax
        restTaxMockMvc.perform(get("/api/taxes/{id}", tax.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tax.getId().intValue()))
            .andExpect(jsonPath("$.clientId").value(DEFAULT_CLIENT_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTax() throws Exception {
        // Get the tax
        restTaxMockMvc.perform(get("/api/taxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTax() throws Exception {
        // Initialize the database
        taxRepository.saveAndFlush(tax);

        int databaseSizeBeforeUpdate = taxRepository.findAll().size();

        // Update the tax
        Tax updatedTax = taxRepository.findById(tax.getId()).get();
        // Disconnect from session so that the updates on updatedTax are not directly saved in db
        em.detach(updatedTax);
        updatedTax
            .clientId(UPDATED_CLIENT_ID)
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .status(UPDATED_STATUS);
        TaxDTO taxDTO = taxMapper.toDto(updatedTax);

        restTaxMockMvc.perform(put("/api/taxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxDTO)))
            .andExpect(status().isOk());

        // Validate the Tax in the database
        List<Tax> taxList = taxRepository.findAll();
        assertThat(taxList).hasSize(databaseSizeBeforeUpdate);
        Tax testTax = taxList.get(taxList.size() - 1);
        assertThat(testTax.getClientId()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testTax.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTax.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTax.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingTax() throws Exception {
        int databaseSizeBeforeUpdate = taxRepository.findAll().size();

        // Create the Tax
        TaxDTO taxDTO = taxMapper.toDto(tax);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaxMockMvc.perform(put("/api/taxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tax in the database
        List<Tax> taxList = taxRepository.findAll();
        assertThat(taxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTax() throws Exception {
        // Initialize the database
        taxRepository.saveAndFlush(tax);

        int databaseSizeBeforeDelete = taxRepository.findAll().size();

        // Delete the tax
        restTaxMockMvc.perform(delete("/api/taxes/{id}", tax.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tax> taxList = taxRepository.findAll();
        assertThat(taxList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
