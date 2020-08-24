package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.Way2InvoiceApp;
import com.way2invoice.bms.domain.AccountGroup;
import com.way2invoice.bms.repository.AccountGroupRepository;
import com.way2invoice.bms.service.AccountGroupService;
import com.way2invoice.bms.service.dto.AccountGroupDTO;
import com.way2invoice.bms.service.mapper.AccountGroupMapper;

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
 * Integration tests for the {@link AccountGroupResource} REST controller.
 */
@SpringBootTest(classes = Way2InvoiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AccountGroupResourceIT {

    private static final Long DEFAULT_CLIENT_ID = 1L;
    private static final Long UPDATED_CLIENT_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.DEACTIVE;

    @Autowired
    private AccountGroupRepository accountGroupRepository;

    @Autowired
    private AccountGroupMapper accountGroupMapper;

    @Autowired
    private AccountGroupService accountGroupService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAccountGroupMockMvc;

    private AccountGroup accountGroup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountGroup createEntity(EntityManager em) {
        AccountGroup accountGroup = new AccountGroup()
            .clientId(DEFAULT_CLIENT_ID)
            .name(DEFAULT_NAME)
            .status(DEFAULT_STATUS);
        return accountGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountGroup createUpdatedEntity(EntityManager em) {
        AccountGroup accountGroup = new AccountGroup()
            .clientId(UPDATED_CLIENT_ID)
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS);
        return accountGroup;
    }

    @BeforeEach
    public void initTest() {
        accountGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccountGroup() throws Exception {
        int databaseSizeBeforeCreate = accountGroupRepository.findAll().size();
        // Create the AccountGroup
        AccountGroupDTO accountGroupDTO = accountGroupMapper.toDto(accountGroup);
        restAccountGroupMockMvc.perform(post("/api/account-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the AccountGroup in the database
        List<AccountGroup> accountGroupList = accountGroupRepository.findAll();
        assertThat(accountGroupList).hasSize(databaseSizeBeforeCreate + 1);
        AccountGroup testAccountGroup = accountGroupList.get(accountGroupList.size() - 1);
        assertThat(testAccountGroup.getClientId()).isEqualTo(DEFAULT_CLIENT_ID);
        assertThat(testAccountGroup.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAccountGroup.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createAccountGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accountGroupRepository.findAll().size();

        // Create the AccountGroup with an existing ID
        accountGroup.setId(1L);
        AccountGroupDTO accountGroupDTO = accountGroupMapper.toDto(accountGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountGroupMockMvc.perform(post("/api/account-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountGroup in the database
        List<AccountGroup> accountGroupList = accountGroupRepository.findAll();
        assertThat(accountGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkClientIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountGroupRepository.findAll().size();
        // set the field null
        accountGroup.setClientId(null);

        // Create the AccountGroup, which fails.
        AccountGroupDTO accountGroupDTO = accountGroupMapper.toDto(accountGroup);


        restAccountGroupMockMvc.perform(post("/api/account-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountGroupDTO)))
            .andExpect(status().isBadRequest());

        List<AccountGroup> accountGroupList = accountGroupRepository.findAll();
        assertThat(accountGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountGroupRepository.findAll().size();
        // set the field null
        accountGroup.setName(null);

        // Create the AccountGroup, which fails.
        AccountGroupDTO accountGroupDTO = accountGroupMapper.toDto(accountGroup);


        restAccountGroupMockMvc.perform(post("/api/account-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountGroupDTO)))
            .andExpect(status().isBadRequest());

        List<AccountGroup> accountGroupList = accountGroupRepository.findAll();
        assertThat(accountGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountGroupRepository.findAll().size();
        // set the field null
        accountGroup.setStatus(null);

        // Create the AccountGroup, which fails.
        AccountGroupDTO accountGroupDTO = accountGroupMapper.toDto(accountGroup);


        restAccountGroupMockMvc.perform(post("/api/account-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountGroupDTO)))
            .andExpect(status().isBadRequest());

        List<AccountGroup> accountGroupList = accountGroupRepository.findAll();
        assertThat(accountGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAccountGroups() throws Exception {
        // Initialize the database
        accountGroupRepository.saveAndFlush(accountGroup);

        // Get all the accountGroupList
        restAccountGroupMockMvc.perform(get("/api/account-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].clientId").value(hasItem(DEFAULT_CLIENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getAccountGroup() throws Exception {
        // Initialize the database
        accountGroupRepository.saveAndFlush(accountGroup);

        // Get the accountGroup
        restAccountGroupMockMvc.perform(get("/api/account-groups/{id}", accountGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(accountGroup.getId().intValue()))
            .andExpect(jsonPath("$.clientId").value(DEFAULT_CLIENT_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAccountGroup() throws Exception {
        // Get the accountGroup
        restAccountGroupMockMvc.perform(get("/api/account-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccountGroup() throws Exception {
        // Initialize the database
        accountGroupRepository.saveAndFlush(accountGroup);

        int databaseSizeBeforeUpdate = accountGroupRepository.findAll().size();

        // Update the accountGroup
        AccountGroup updatedAccountGroup = accountGroupRepository.findById(accountGroup.getId()).get();
        // Disconnect from session so that the updates on updatedAccountGroup are not directly saved in db
        em.detach(updatedAccountGroup);
        updatedAccountGroup
            .clientId(UPDATED_CLIENT_ID)
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS);
        AccountGroupDTO accountGroupDTO = accountGroupMapper.toDto(updatedAccountGroup);

        restAccountGroupMockMvc.perform(put("/api/account-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountGroupDTO)))
            .andExpect(status().isOk());

        // Validate the AccountGroup in the database
        List<AccountGroup> accountGroupList = accountGroupRepository.findAll();
        assertThat(accountGroupList).hasSize(databaseSizeBeforeUpdate);
        AccountGroup testAccountGroup = accountGroupList.get(accountGroupList.size() - 1);
        assertThat(testAccountGroup.getClientId()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testAccountGroup.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAccountGroup.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingAccountGroup() throws Exception {
        int databaseSizeBeforeUpdate = accountGroupRepository.findAll().size();

        // Create the AccountGroup
        AccountGroupDTO accountGroupDTO = accountGroupMapper.toDto(accountGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountGroupMockMvc.perform(put("/api/account-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountGroup in the database
        List<AccountGroup> accountGroupList = accountGroupRepository.findAll();
        assertThat(accountGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccountGroup() throws Exception {
        // Initialize the database
        accountGroupRepository.saveAndFlush(accountGroup);

        int databaseSizeBeforeDelete = accountGroupRepository.findAll().size();

        // Delete the accountGroup
        restAccountGroupMockMvc.perform(delete("/api/account-groups/{id}", accountGroup.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AccountGroup> accountGroupList = accountGroupRepository.findAll();
        assertThat(accountGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
