package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.Way2InvoiceApp;
import com.way2invoice.bms.domain.BillingPeriod;
import com.way2invoice.bms.repository.BillingPeriodRepository;
import com.way2invoice.bms.service.BillingPeriodService;
import com.way2invoice.bms.service.dto.BillingPeriodDTO;
import com.way2invoice.bms.service.mapper.BillingPeriodMapper;

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
 * Integration tests for the {@link BillingPeriodResource} REST controller.
 */
@SpringBootTest(classes = Way2InvoiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BillingPeriodResourceIT {

    private static final Long DEFAULT_CLIENT_ID = 1L;
    private static final Long UPDATED_CLIENT_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.DEACTIVE;

    @Autowired
    private BillingPeriodRepository billingPeriodRepository;

    @Autowired
    private BillingPeriodMapper billingPeriodMapper;

    @Autowired
    private BillingPeriodService billingPeriodService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBillingPeriodMockMvc;

    private BillingPeriod billingPeriod;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillingPeriod createEntity(EntityManager em) {
        BillingPeriod billingPeriod = new BillingPeriod()
            .clientId(DEFAULT_CLIENT_ID)
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .status(DEFAULT_STATUS);
        return billingPeriod;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillingPeriod createUpdatedEntity(EntityManager em) {
        BillingPeriod billingPeriod = new BillingPeriod()
            .clientId(UPDATED_CLIENT_ID)
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .status(UPDATED_STATUS);
        return billingPeriod;
    }

    @BeforeEach
    public void initTest() {
        billingPeriod = createEntity(em);
    }

    @Test
    @Transactional
    public void createBillingPeriod() throws Exception {
        int databaseSizeBeforeCreate = billingPeriodRepository.findAll().size();
        // Create the BillingPeriod
        BillingPeriodDTO billingPeriodDTO = billingPeriodMapper.toDto(billingPeriod);
        restBillingPeriodMockMvc.perform(post("/api/billing-periods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingPeriodDTO)))
            .andExpect(status().isCreated());

        // Validate the BillingPeriod in the database
        List<BillingPeriod> billingPeriodList = billingPeriodRepository.findAll();
        assertThat(billingPeriodList).hasSize(databaseSizeBeforeCreate + 1);
        BillingPeriod testBillingPeriod = billingPeriodList.get(billingPeriodList.size() - 1);
        assertThat(testBillingPeriod.getClientId()).isEqualTo(DEFAULT_CLIENT_ID);
        assertThat(testBillingPeriod.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBillingPeriod.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testBillingPeriod.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createBillingPeriodWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = billingPeriodRepository.findAll().size();

        // Create the BillingPeriod with an existing ID
        billingPeriod.setId(1L);
        BillingPeriodDTO billingPeriodDTO = billingPeriodMapper.toDto(billingPeriod);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBillingPeriodMockMvc.perform(post("/api/billing-periods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingPeriodDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BillingPeriod in the database
        List<BillingPeriod> billingPeriodList = billingPeriodRepository.findAll();
        assertThat(billingPeriodList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkClientIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = billingPeriodRepository.findAll().size();
        // set the field null
        billingPeriod.setClientId(null);

        // Create the BillingPeriod, which fails.
        BillingPeriodDTO billingPeriodDTO = billingPeriodMapper.toDto(billingPeriod);


        restBillingPeriodMockMvc.perform(post("/api/billing-periods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingPeriodDTO)))
            .andExpect(status().isBadRequest());

        List<BillingPeriod> billingPeriodList = billingPeriodRepository.findAll();
        assertThat(billingPeriodList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = billingPeriodRepository.findAll().size();
        // set the field null
        billingPeriod.setName(null);

        // Create the BillingPeriod, which fails.
        BillingPeriodDTO billingPeriodDTO = billingPeriodMapper.toDto(billingPeriod);


        restBillingPeriodMockMvc.perform(post("/api/billing-periods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingPeriodDTO)))
            .andExpect(status().isBadRequest());

        List<BillingPeriod> billingPeriodList = billingPeriodRepository.findAll();
        assertThat(billingPeriodList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBillingPeriods() throws Exception {
        // Initialize the database
        billingPeriodRepository.saveAndFlush(billingPeriod);

        // Get all the billingPeriodList
        restBillingPeriodMockMvc.perform(get("/api/billing-periods?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(billingPeriod.getId().intValue())))
            .andExpect(jsonPath("$.[*].clientId").value(hasItem(DEFAULT_CLIENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getBillingPeriod() throws Exception {
        // Initialize the database
        billingPeriodRepository.saveAndFlush(billingPeriod);

        // Get the billingPeriod
        restBillingPeriodMockMvc.perform(get("/api/billing-periods/{id}", billingPeriod.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(billingPeriod.getId().intValue()))
            .andExpect(jsonPath("$.clientId").value(DEFAULT_CLIENT_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBillingPeriod() throws Exception {
        // Get the billingPeriod
        restBillingPeriodMockMvc.perform(get("/api/billing-periods/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBillingPeriod() throws Exception {
        // Initialize the database
        billingPeriodRepository.saveAndFlush(billingPeriod);

        int databaseSizeBeforeUpdate = billingPeriodRepository.findAll().size();

        // Update the billingPeriod
        BillingPeriod updatedBillingPeriod = billingPeriodRepository.findById(billingPeriod.getId()).get();
        // Disconnect from session so that the updates on updatedBillingPeriod are not directly saved in db
        em.detach(updatedBillingPeriod);
        updatedBillingPeriod
            .clientId(UPDATED_CLIENT_ID)
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .status(UPDATED_STATUS);
        BillingPeriodDTO billingPeriodDTO = billingPeriodMapper.toDto(updatedBillingPeriod);

        restBillingPeriodMockMvc.perform(put("/api/billing-periods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingPeriodDTO)))
            .andExpect(status().isOk());

        // Validate the BillingPeriod in the database
        List<BillingPeriod> billingPeriodList = billingPeriodRepository.findAll();
        assertThat(billingPeriodList).hasSize(databaseSizeBeforeUpdate);
        BillingPeriod testBillingPeriod = billingPeriodList.get(billingPeriodList.size() - 1);
        assertThat(testBillingPeriod.getClientId()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testBillingPeriod.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBillingPeriod.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testBillingPeriod.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingBillingPeriod() throws Exception {
        int databaseSizeBeforeUpdate = billingPeriodRepository.findAll().size();

        // Create the BillingPeriod
        BillingPeriodDTO billingPeriodDTO = billingPeriodMapper.toDto(billingPeriod);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBillingPeriodMockMvc.perform(put("/api/billing-periods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingPeriodDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BillingPeriod in the database
        List<BillingPeriod> billingPeriodList = billingPeriodRepository.findAll();
        assertThat(billingPeriodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBillingPeriod() throws Exception {
        // Initialize the database
        billingPeriodRepository.saveAndFlush(billingPeriod);

        int databaseSizeBeforeDelete = billingPeriodRepository.findAll().size();

        // Delete the billingPeriod
        restBillingPeriodMockMvc.perform(delete("/api/billing-periods/{id}", billingPeriod.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BillingPeriod> billingPeriodList = billingPeriodRepository.findAll();
        assertThat(billingPeriodList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
