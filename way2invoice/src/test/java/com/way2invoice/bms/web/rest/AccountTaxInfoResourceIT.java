package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.Way2InvoiceApp;
import com.way2invoice.bms.domain.AccountTaxInfo;
import com.way2invoice.bms.repository.AccountTaxInfoRepository;
import com.way2invoice.bms.service.AccountTaxInfoService;
import com.way2invoice.bms.service.dto.AccountTaxInfoDTO;
import com.way2invoice.bms.service.mapper.AccountTaxInfoMapper;

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

/**
 * Integration tests for the {@link AccountTaxInfoResource} REST controller.
 */
@SpringBootTest(classes = Way2InvoiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AccountTaxInfoResourceIT {

    private static final String DEFAULT_TIN = "AAAAAAAAAA";
    private static final String UPDATED_TIN = "BBBBBBBBBB";

    private static final String DEFAULT_GST = "AAAAAAAAAA";
    private static final String UPDATED_GST = "BBBBBBBBBB";

    private static final String DEFAULT_PAN = "AAAAAAAAAA";
    private static final String UPDATED_PAN = "BBBBBBBBBB";

    @Autowired
    private AccountTaxInfoRepository accountTaxInfoRepository;

    @Autowired
    private AccountTaxInfoMapper accountTaxInfoMapper;

    @Autowired
    private AccountTaxInfoService accountTaxInfoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAccountTaxInfoMockMvc;

    private AccountTaxInfo accountTaxInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountTaxInfo createEntity(EntityManager em) {
        AccountTaxInfo accountTaxInfo = new AccountTaxInfo()
            .tin(DEFAULT_TIN)
            .gst(DEFAULT_GST)
            .pan(DEFAULT_PAN);
        return accountTaxInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountTaxInfo createUpdatedEntity(EntityManager em) {
        AccountTaxInfo accountTaxInfo = new AccountTaxInfo()
            .tin(UPDATED_TIN)
            .gst(UPDATED_GST)
            .pan(UPDATED_PAN);
        return accountTaxInfo;
    }

    @BeforeEach
    public void initTest() {
        accountTaxInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccountTaxInfo() throws Exception {
        int databaseSizeBeforeCreate = accountTaxInfoRepository.findAll().size();
        // Create the AccountTaxInfo
        AccountTaxInfoDTO accountTaxInfoDTO = accountTaxInfoMapper.toDto(accountTaxInfo);
        restAccountTaxInfoMockMvc.perform(post("/api/account-tax-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountTaxInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the AccountTaxInfo in the database
        List<AccountTaxInfo> accountTaxInfoList = accountTaxInfoRepository.findAll();
        assertThat(accountTaxInfoList).hasSize(databaseSizeBeforeCreate + 1);
        AccountTaxInfo testAccountTaxInfo = accountTaxInfoList.get(accountTaxInfoList.size() - 1);
        assertThat(testAccountTaxInfo.getTin()).isEqualTo(DEFAULT_TIN);
        assertThat(testAccountTaxInfo.getGst()).isEqualTo(DEFAULT_GST);
        assertThat(testAccountTaxInfo.getPan()).isEqualTo(DEFAULT_PAN);
    }

    @Test
    @Transactional
    public void createAccountTaxInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accountTaxInfoRepository.findAll().size();

        // Create the AccountTaxInfo with an existing ID
        accountTaxInfo.setId(1L);
        AccountTaxInfoDTO accountTaxInfoDTO = accountTaxInfoMapper.toDto(accountTaxInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountTaxInfoMockMvc.perform(post("/api/account-tax-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountTaxInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountTaxInfo in the database
        List<AccountTaxInfo> accountTaxInfoList = accountTaxInfoRepository.findAll();
        assertThat(accountTaxInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAccountTaxInfos() throws Exception {
        // Initialize the database
        accountTaxInfoRepository.saveAndFlush(accountTaxInfo);

        // Get all the accountTaxInfoList
        restAccountTaxInfoMockMvc.perform(get("/api/account-tax-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountTaxInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].tin").value(hasItem(DEFAULT_TIN)))
            .andExpect(jsonPath("$.[*].gst").value(hasItem(DEFAULT_GST)))
            .andExpect(jsonPath("$.[*].pan").value(hasItem(DEFAULT_PAN)));
    }
    
    @Test
    @Transactional
    public void getAccountTaxInfo() throws Exception {
        // Initialize the database
        accountTaxInfoRepository.saveAndFlush(accountTaxInfo);

        // Get the accountTaxInfo
        restAccountTaxInfoMockMvc.perform(get("/api/account-tax-infos/{id}", accountTaxInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(accountTaxInfo.getId().intValue()))
            .andExpect(jsonPath("$.tin").value(DEFAULT_TIN))
            .andExpect(jsonPath("$.gst").value(DEFAULT_GST))
            .andExpect(jsonPath("$.pan").value(DEFAULT_PAN));
    }
    @Test
    @Transactional
    public void getNonExistingAccountTaxInfo() throws Exception {
        // Get the accountTaxInfo
        restAccountTaxInfoMockMvc.perform(get("/api/account-tax-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccountTaxInfo() throws Exception {
        // Initialize the database
        accountTaxInfoRepository.saveAndFlush(accountTaxInfo);

        int databaseSizeBeforeUpdate = accountTaxInfoRepository.findAll().size();

        // Update the accountTaxInfo
        AccountTaxInfo updatedAccountTaxInfo = accountTaxInfoRepository.findById(accountTaxInfo.getId()).get();
        // Disconnect from session so that the updates on updatedAccountTaxInfo are not directly saved in db
        em.detach(updatedAccountTaxInfo);
        updatedAccountTaxInfo
            .tin(UPDATED_TIN)
            .gst(UPDATED_GST)
            .pan(UPDATED_PAN);
        AccountTaxInfoDTO accountTaxInfoDTO = accountTaxInfoMapper.toDto(updatedAccountTaxInfo);

        restAccountTaxInfoMockMvc.perform(put("/api/account-tax-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountTaxInfoDTO)))
            .andExpect(status().isOk());

        // Validate the AccountTaxInfo in the database
        List<AccountTaxInfo> accountTaxInfoList = accountTaxInfoRepository.findAll();
        assertThat(accountTaxInfoList).hasSize(databaseSizeBeforeUpdate);
        AccountTaxInfo testAccountTaxInfo = accountTaxInfoList.get(accountTaxInfoList.size() - 1);
        assertThat(testAccountTaxInfo.getTin()).isEqualTo(UPDATED_TIN);
        assertThat(testAccountTaxInfo.getGst()).isEqualTo(UPDATED_GST);
        assertThat(testAccountTaxInfo.getPan()).isEqualTo(UPDATED_PAN);
    }

    @Test
    @Transactional
    public void updateNonExistingAccountTaxInfo() throws Exception {
        int databaseSizeBeforeUpdate = accountTaxInfoRepository.findAll().size();

        // Create the AccountTaxInfo
        AccountTaxInfoDTO accountTaxInfoDTO = accountTaxInfoMapper.toDto(accountTaxInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountTaxInfoMockMvc.perform(put("/api/account-tax-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountTaxInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountTaxInfo in the database
        List<AccountTaxInfo> accountTaxInfoList = accountTaxInfoRepository.findAll();
        assertThat(accountTaxInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccountTaxInfo() throws Exception {
        // Initialize the database
        accountTaxInfoRepository.saveAndFlush(accountTaxInfo);

        int databaseSizeBeforeDelete = accountTaxInfoRepository.findAll().size();

        // Delete the accountTaxInfo
        restAccountTaxInfoMockMvc.perform(delete("/api/account-tax-infos/{id}", accountTaxInfo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AccountTaxInfo> accountTaxInfoList = accountTaxInfoRepository.findAll();
        assertThat(accountTaxInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
