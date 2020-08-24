package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.Way2InvoiceApp;
import com.way2invoice.bms.domain.CustomerCategory;
import com.way2invoice.bms.repository.CustomerCategoryRepository;
import com.way2invoice.bms.service.CustomerCategoryService;
import com.way2invoice.bms.service.dto.CustomerCategoryDTO;
import com.way2invoice.bms.service.mapper.CustomerCategoryMapper;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.way2invoice.bms.domain.enumeration.Status;
/**
 * Integration tests for the {@link CustomerCategoryResource} REST controller.
 */
@SpringBootTest(classes = Way2InvoiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CustomerCategoryResourceIT {

    private static final Long DEFAULT_CLIENT_ID = 1L;
    private static final Long UPDATED_CLIENT_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BILL_CREDIT_LIMIT = new BigDecimal(1);
    private static final BigDecimal UPDATED_BILL_CREDIT_LIMIT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CREDIT_LIMIT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CREDIT_LIMIT = new BigDecimal(2);

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.DEACTIVE;

    @Autowired
    private CustomerCategoryRepository customerCategoryRepository;

    @Autowired
    private CustomerCategoryMapper customerCategoryMapper;

    @Autowired
    private CustomerCategoryService customerCategoryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCustomerCategoryMockMvc;

    private CustomerCategory customerCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerCategory createEntity(EntityManager em) {
        CustomerCategory customerCategory = new CustomerCategory()
            .clientId(DEFAULT_CLIENT_ID)
            .name(DEFAULT_NAME)
            .billCreditLimit(DEFAULT_BILL_CREDIT_LIMIT)
            .creditLimit(DEFAULT_CREDIT_LIMIT)
            .status(DEFAULT_STATUS);
        return customerCategory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerCategory createUpdatedEntity(EntityManager em) {
        CustomerCategory customerCategory = new CustomerCategory()
            .clientId(UPDATED_CLIENT_ID)
            .name(UPDATED_NAME)
            .billCreditLimit(UPDATED_BILL_CREDIT_LIMIT)
            .creditLimit(UPDATED_CREDIT_LIMIT)
            .status(UPDATED_STATUS);
        return customerCategory;
    }

    @BeforeEach
    public void initTest() {
        customerCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomerCategory() throws Exception {
        int databaseSizeBeforeCreate = customerCategoryRepository.findAll().size();
        // Create the CustomerCategory
        CustomerCategoryDTO customerCategoryDTO = customerCategoryMapper.toDto(customerCategory);
        restCustomerCategoryMockMvc.perform(post("/api/customer-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the CustomerCategory in the database
        List<CustomerCategory> customerCategoryList = customerCategoryRepository.findAll();
        assertThat(customerCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        CustomerCategory testCustomerCategory = customerCategoryList.get(customerCategoryList.size() - 1);
        assertThat(testCustomerCategory.getClientId()).isEqualTo(DEFAULT_CLIENT_ID);
        assertThat(testCustomerCategory.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCustomerCategory.getBillCreditLimit()).isEqualTo(DEFAULT_BILL_CREDIT_LIMIT);
        assertThat(testCustomerCategory.getCreditLimit()).isEqualTo(DEFAULT_CREDIT_LIMIT);
        assertThat(testCustomerCategory.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createCustomerCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerCategoryRepository.findAll().size();

        // Create the CustomerCategory with an existing ID
        customerCategory.setId(1L);
        CustomerCategoryDTO customerCategoryDTO = customerCategoryMapper.toDto(customerCategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerCategoryMockMvc.perform(post("/api/customer-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerCategory in the database
        List<CustomerCategory> customerCategoryList = customerCategoryRepository.findAll();
        assertThat(customerCategoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkClientIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerCategoryRepository.findAll().size();
        // set the field null
        customerCategory.setClientId(null);

        // Create the CustomerCategory, which fails.
        CustomerCategoryDTO customerCategoryDTO = customerCategoryMapper.toDto(customerCategory);


        restCustomerCategoryMockMvc.perform(post("/api/customer-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerCategoryDTO)))
            .andExpect(status().isBadRequest());

        List<CustomerCategory> customerCategoryList = customerCategoryRepository.findAll();
        assertThat(customerCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerCategoryRepository.findAll().size();
        // set the field null
        customerCategory.setName(null);

        // Create the CustomerCategory, which fails.
        CustomerCategoryDTO customerCategoryDTO = customerCategoryMapper.toDto(customerCategory);


        restCustomerCategoryMockMvc.perform(post("/api/customer-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerCategoryDTO)))
            .andExpect(status().isBadRequest());

        List<CustomerCategory> customerCategoryList = customerCategoryRepository.findAll();
        assertThat(customerCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBillCreditLimitIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerCategoryRepository.findAll().size();
        // set the field null
        customerCategory.setBillCreditLimit(null);

        // Create the CustomerCategory, which fails.
        CustomerCategoryDTO customerCategoryDTO = customerCategoryMapper.toDto(customerCategory);


        restCustomerCategoryMockMvc.perform(post("/api/customer-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerCategoryDTO)))
            .andExpect(status().isBadRequest());

        List<CustomerCategory> customerCategoryList = customerCategoryRepository.findAll();
        assertThat(customerCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreditLimitIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerCategoryRepository.findAll().size();
        // set the field null
        customerCategory.setCreditLimit(null);

        // Create the CustomerCategory, which fails.
        CustomerCategoryDTO customerCategoryDTO = customerCategoryMapper.toDto(customerCategory);


        restCustomerCategoryMockMvc.perform(post("/api/customer-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerCategoryDTO)))
            .andExpect(status().isBadRequest());

        List<CustomerCategory> customerCategoryList = customerCategoryRepository.findAll();
        assertThat(customerCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerCategoryRepository.findAll().size();
        // set the field null
        customerCategory.setStatus(null);

        // Create the CustomerCategory, which fails.
        CustomerCategoryDTO customerCategoryDTO = customerCategoryMapper.toDto(customerCategory);


        restCustomerCategoryMockMvc.perform(post("/api/customer-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerCategoryDTO)))
            .andExpect(status().isBadRequest());

        List<CustomerCategory> customerCategoryList = customerCategoryRepository.findAll();
        assertThat(customerCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCustomerCategories() throws Exception {
        // Initialize the database
        customerCategoryRepository.saveAndFlush(customerCategory);

        // Get all the customerCategoryList
        restCustomerCategoryMockMvc.perform(get("/api/customer-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].clientId").value(hasItem(DEFAULT_CLIENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].billCreditLimit").value(hasItem(DEFAULT_BILL_CREDIT_LIMIT.intValue())))
            .andExpect(jsonPath("$.[*].creditLimit").value(hasItem(DEFAULT_CREDIT_LIMIT.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getCustomerCategory() throws Exception {
        // Initialize the database
        customerCategoryRepository.saveAndFlush(customerCategory);

        // Get the customerCategory
        restCustomerCategoryMockMvc.perform(get("/api/customer-categories/{id}", customerCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(customerCategory.getId().intValue()))
            .andExpect(jsonPath("$.clientId").value(DEFAULT_CLIENT_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.billCreditLimit").value(DEFAULT_BILL_CREDIT_LIMIT.intValue()))
            .andExpect(jsonPath("$.creditLimit").value(DEFAULT_CREDIT_LIMIT.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCustomerCategory() throws Exception {
        // Get the customerCategory
        restCustomerCategoryMockMvc.perform(get("/api/customer-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomerCategory() throws Exception {
        // Initialize the database
        customerCategoryRepository.saveAndFlush(customerCategory);

        int databaseSizeBeforeUpdate = customerCategoryRepository.findAll().size();

        // Update the customerCategory
        CustomerCategory updatedCustomerCategory = customerCategoryRepository.findById(customerCategory.getId()).get();
        // Disconnect from session so that the updates on updatedCustomerCategory are not directly saved in db
        em.detach(updatedCustomerCategory);
        updatedCustomerCategory
            .clientId(UPDATED_CLIENT_ID)
            .name(UPDATED_NAME)
            .billCreditLimit(UPDATED_BILL_CREDIT_LIMIT)
            .creditLimit(UPDATED_CREDIT_LIMIT)
            .status(UPDATED_STATUS);
        CustomerCategoryDTO customerCategoryDTO = customerCategoryMapper.toDto(updatedCustomerCategory);

        restCustomerCategoryMockMvc.perform(put("/api/customer-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerCategoryDTO)))
            .andExpect(status().isOk());

        // Validate the CustomerCategory in the database
        List<CustomerCategory> customerCategoryList = customerCategoryRepository.findAll();
        assertThat(customerCategoryList).hasSize(databaseSizeBeforeUpdate);
        CustomerCategory testCustomerCategory = customerCategoryList.get(customerCategoryList.size() - 1);
        assertThat(testCustomerCategory.getClientId()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testCustomerCategory.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCustomerCategory.getBillCreditLimit()).isEqualTo(UPDATED_BILL_CREDIT_LIMIT);
        assertThat(testCustomerCategory.getCreditLimit()).isEqualTo(UPDATED_CREDIT_LIMIT);
        assertThat(testCustomerCategory.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomerCategory() throws Exception {
        int databaseSizeBeforeUpdate = customerCategoryRepository.findAll().size();

        // Create the CustomerCategory
        CustomerCategoryDTO customerCategoryDTO = customerCategoryMapper.toDto(customerCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerCategoryMockMvc.perform(put("/api/customer-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerCategory in the database
        List<CustomerCategory> customerCategoryList = customerCategoryRepository.findAll();
        assertThat(customerCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCustomerCategory() throws Exception {
        // Initialize the database
        customerCategoryRepository.saveAndFlush(customerCategory);

        int databaseSizeBeforeDelete = customerCategoryRepository.findAll().size();

        // Delete the customerCategory
        restCustomerCategoryMockMvc.perform(delete("/api/customer-categories/{id}", customerCategory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CustomerCategory> customerCategoryList = customerCategoryRepository.findAll();
        assertThat(customerCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
