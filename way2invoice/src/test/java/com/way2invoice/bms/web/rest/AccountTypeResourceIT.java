package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.Way2InvoiceApp;
import com.way2invoice.bms.domain.AccountType;
import com.way2invoice.bms.repository.AccountTypeRepository;
import com.way2invoice.bms.service.AccountTypeService;
import com.way2invoice.bms.service.dto.AccountTypeDTO;
import com.way2invoice.bms.service.mapper.AccountTypeMapper;

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
 * Integration tests for the {@link AccountTypeResource} REST controller.
 */
@SpringBootTest(classes = Way2InvoiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AccountTypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.DEACTIVE;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private AccountTypeMapper accountTypeMapper;

    @Autowired
    private AccountTypeService accountTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAccountTypeMockMvc;

    private AccountType accountType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountType createEntity(EntityManager em) {
        AccountType accountType = new AccountType()
            .name(DEFAULT_NAME)
            .code(DEFAULT_CODE)
            .status(DEFAULT_STATUS);
        return accountType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountType createUpdatedEntity(EntityManager em) {
        AccountType accountType = new AccountType()
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .status(UPDATED_STATUS);
        return accountType;
    }

    @BeforeEach
    public void initTest() {
        accountType = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccountType() throws Exception {
        int databaseSizeBeforeCreate = accountTypeRepository.findAll().size();
        // Create the AccountType
        AccountTypeDTO accountTypeDTO = accountTypeMapper.toDto(accountType);
        restAccountTypeMockMvc.perform(post("/api/account-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the AccountType in the database
        List<AccountType> accountTypeList = accountTypeRepository.findAll();
        assertThat(accountTypeList).hasSize(databaseSizeBeforeCreate + 1);
        AccountType testAccountType = accountTypeList.get(accountTypeList.size() - 1);
        assertThat(testAccountType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAccountType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testAccountType.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createAccountTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accountTypeRepository.findAll().size();

        // Create the AccountType with an existing ID
        accountType.setId(1L);
        AccountTypeDTO accountTypeDTO = accountTypeMapper.toDto(accountType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountTypeMockMvc.perform(post("/api/account-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountType in the database
        List<AccountType> accountTypeList = accountTypeRepository.findAll();
        assertThat(accountTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountTypeRepository.findAll().size();
        // set the field null
        accountType.setName(null);

        // Create the AccountType, which fails.
        AccountTypeDTO accountTypeDTO = accountTypeMapper.toDto(accountType);


        restAccountTypeMockMvc.perform(post("/api/account-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountTypeDTO)))
            .andExpect(status().isBadRequest());

        List<AccountType> accountTypeList = accountTypeRepository.findAll();
        assertThat(accountTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountTypeRepository.findAll().size();
        // set the field null
        accountType.setCode(null);

        // Create the AccountType, which fails.
        AccountTypeDTO accountTypeDTO = accountTypeMapper.toDto(accountType);


        restAccountTypeMockMvc.perform(post("/api/account-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountTypeDTO)))
            .andExpect(status().isBadRequest());

        List<AccountType> accountTypeList = accountTypeRepository.findAll();
        assertThat(accountTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAccountTypes() throws Exception {
        // Initialize the database
        accountTypeRepository.saveAndFlush(accountType);

        // Get all the accountTypeList
        restAccountTypeMockMvc.perform(get("/api/account-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getAccountType() throws Exception {
        // Initialize the database
        accountTypeRepository.saveAndFlush(accountType);

        // Get the accountType
        restAccountTypeMockMvc.perform(get("/api/account-types/{id}", accountType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(accountType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAccountType() throws Exception {
        // Get the accountType
        restAccountTypeMockMvc.perform(get("/api/account-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccountType() throws Exception {
        // Initialize the database
        accountTypeRepository.saveAndFlush(accountType);

        int databaseSizeBeforeUpdate = accountTypeRepository.findAll().size();

        // Update the accountType
        AccountType updatedAccountType = accountTypeRepository.findById(accountType.getId()).get();
        // Disconnect from session so that the updates on updatedAccountType are not directly saved in db
        em.detach(updatedAccountType);
        updatedAccountType
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .status(UPDATED_STATUS);
        AccountTypeDTO accountTypeDTO = accountTypeMapper.toDto(updatedAccountType);

        restAccountTypeMockMvc.perform(put("/api/account-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountTypeDTO)))
            .andExpect(status().isOk());

        // Validate the AccountType in the database
        List<AccountType> accountTypeList = accountTypeRepository.findAll();
        assertThat(accountTypeList).hasSize(databaseSizeBeforeUpdate);
        AccountType testAccountType = accountTypeList.get(accountTypeList.size() - 1);
        assertThat(testAccountType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAccountType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testAccountType.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingAccountType() throws Exception {
        int databaseSizeBeforeUpdate = accountTypeRepository.findAll().size();

        // Create the AccountType
        AccountTypeDTO accountTypeDTO = accountTypeMapper.toDto(accountType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountTypeMockMvc.perform(put("/api/account-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountType in the database
        List<AccountType> accountTypeList = accountTypeRepository.findAll();
        assertThat(accountTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccountType() throws Exception {
        // Initialize the database
        accountTypeRepository.saveAndFlush(accountType);

        int databaseSizeBeforeDelete = accountTypeRepository.findAll().size();

        // Delete the accountType
        restAccountTypeMockMvc.perform(delete("/api/account-types/{id}", accountType.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AccountType> accountTypeList = accountTypeRepository.findAll();
        assertThat(accountTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
