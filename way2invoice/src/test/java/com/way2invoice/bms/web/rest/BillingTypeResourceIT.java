package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.Way2InvoiceApp;
import com.way2invoice.bms.domain.BillingType;
import com.way2invoice.bms.repository.BillingTypeRepository;
import com.way2invoice.bms.service.BillingTypeService;
import com.way2invoice.bms.service.dto.BillingTypeDTO;
import com.way2invoice.bms.service.mapper.BillingTypeMapper;

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
 * Integration tests for the {@link BillingTypeResource} REST controller.
 */
@SpringBootTest(classes = Way2InvoiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BillingTypeResourceIT {

    private static final Long DEFAULT_CLIENT_ID = 1L;
    private static final Long UPDATED_CLIENT_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.DEACTIVE;

    @Autowired
    private BillingTypeRepository billingTypeRepository;

    @Autowired
    private BillingTypeMapper billingTypeMapper;

    @Autowired
    private BillingTypeService billingTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBillingTypeMockMvc;

    private BillingType billingType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillingType createEntity(EntityManager em) {
        BillingType billingType = new BillingType()
            .clientId(DEFAULT_CLIENT_ID)
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .status(DEFAULT_STATUS);
        return billingType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillingType createUpdatedEntity(EntityManager em) {
        BillingType billingType = new BillingType()
            .clientId(UPDATED_CLIENT_ID)
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .status(UPDATED_STATUS);
        return billingType;
    }

    @BeforeEach
    public void initTest() {
        billingType = createEntity(em);
    }

    @Test
    @Transactional
    public void createBillingType() throws Exception {
        int databaseSizeBeforeCreate = billingTypeRepository.findAll().size();
        // Create the BillingType
        BillingTypeDTO billingTypeDTO = billingTypeMapper.toDto(billingType);
        restBillingTypeMockMvc.perform(post("/api/billing-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the BillingType in the database
        List<BillingType> billingTypeList = billingTypeRepository.findAll();
        assertThat(billingTypeList).hasSize(databaseSizeBeforeCreate + 1);
        BillingType testBillingType = billingTypeList.get(billingTypeList.size() - 1);
        assertThat(testBillingType.getClientId()).isEqualTo(DEFAULT_CLIENT_ID);
        assertThat(testBillingType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBillingType.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testBillingType.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createBillingTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = billingTypeRepository.findAll().size();

        // Create the BillingType with an existing ID
        billingType.setId(1L);
        BillingTypeDTO billingTypeDTO = billingTypeMapper.toDto(billingType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBillingTypeMockMvc.perform(post("/api/billing-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BillingType in the database
        List<BillingType> billingTypeList = billingTypeRepository.findAll();
        assertThat(billingTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkClientIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = billingTypeRepository.findAll().size();
        // set the field null
        billingType.setClientId(null);

        // Create the BillingType, which fails.
        BillingTypeDTO billingTypeDTO = billingTypeMapper.toDto(billingType);


        restBillingTypeMockMvc.perform(post("/api/billing-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingTypeDTO)))
            .andExpect(status().isBadRequest());

        List<BillingType> billingTypeList = billingTypeRepository.findAll();
        assertThat(billingTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = billingTypeRepository.findAll().size();
        // set the field null
        billingType.setName(null);

        // Create the BillingType, which fails.
        BillingTypeDTO billingTypeDTO = billingTypeMapper.toDto(billingType);


        restBillingTypeMockMvc.perform(post("/api/billing-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingTypeDTO)))
            .andExpect(status().isBadRequest());

        List<BillingType> billingTypeList = billingTypeRepository.findAll();
        assertThat(billingTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBillingTypes() throws Exception {
        // Initialize the database
        billingTypeRepository.saveAndFlush(billingType);

        // Get all the billingTypeList
        restBillingTypeMockMvc.perform(get("/api/billing-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(billingType.getId().intValue())))
            .andExpect(jsonPath("$.[*].clientId").value(hasItem(DEFAULT_CLIENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getBillingType() throws Exception {
        // Initialize the database
        billingTypeRepository.saveAndFlush(billingType);

        // Get the billingType
        restBillingTypeMockMvc.perform(get("/api/billing-types/{id}", billingType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(billingType.getId().intValue()))
            .andExpect(jsonPath("$.clientId").value(DEFAULT_CLIENT_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBillingType() throws Exception {
        // Get the billingType
        restBillingTypeMockMvc.perform(get("/api/billing-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBillingType() throws Exception {
        // Initialize the database
        billingTypeRepository.saveAndFlush(billingType);

        int databaseSizeBeforeUpdate = billingTypeRepository.findAll().size();

        // Update the billingType
        BillingType updatedBillingType = billingTypeRepository.findById(billingType.getId()).get();
        // Disconnect from session so that the updates on updatedBillingType are not directly saved in db
        em.detach(updatedBillingType);
        updatedBillingType
            .clientId(UPDATED_CLIENT_ID)
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .status(UPDATED_STATUS);
        BillingTypeDTO billingTypeDTO = billingTypeMapper.toDto(updatedBillingType);

        restBillingTypeMockMvc.perform(put("/api/billing-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingTypeDTO)))
            .andExpect(status().isOk());

        // Validate the BillingType in the database
        List<BillingType> billingTypeList = billingTypeRepository.findAll();
        assertThat(billingTypeList).hasSize(databaseSizeBeforeUpdate);
        BillingType testBillingType = billingTypeList.get(billingTypeList.size() - 1);
        assertThat(testBillingType.getClientId()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testBillingType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBillingType.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testBillingType.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingBillingType() throws Exception {
        int databaseSizeBeforeUpdate = billingTypeRepository.findAll().size();

        // Create the BillingType
        BillingTypeDTO billingTypeDTO = billingTypeMapper.toDto(billingType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBillingTypeMockMvc.perform(put("/api/billing-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BillingType in the database
        List<BillingType> billingTypeList = billingTypeRepository.findAll();
        assertThat(billingTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBillingType() throws Exception {
        // Initialize the database
        billingTypeRepository.saveAndFlush(billingType);

        int databaseSizeBeforeDelete = billingTypeRepository.findAll().size();

        // Delete the billingType
        restBillingTypeMockMvc.perform(delete("/api/billing-types/{id}", billingType.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BillingType> billingTypeList = billingTypeRepository.findAll();
        assertThat(billingTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
