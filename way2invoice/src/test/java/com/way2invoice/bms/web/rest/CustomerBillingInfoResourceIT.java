package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.Way2InvoiceApp;
import com.way2invoice.bms.domain.CustomerBillingInfo;
import com.way2invoice.bms.repository.CustomerBillingInfoRepository;
import com.way2invoice.bms.service.CustomerBillingInfoService;
import com.way2invoice.bms.service.dto.CustomerBillingInfoDTO;
import com.way2invoice.bms.service.mapper.CustomerBillingInfoMapper;

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
 * Integration tests for the {@link CustomerBillingInfoResource} REST controller.
 */
@SpringBootTest(classes = Way2InvoiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CustomerBillingInfoResourceIT {

    private static final String DEFAULT_TIN = "AAAAAAAAAA";
    private static final String UPDATED_TIN = "BBBBBBBBBB";

    private static final String DEFAULT_GST = "AAAAAAAAAA";
    private static final String UPDATED_GST = "BBBBBBBBBB";

    private static final String DEFAULT_PAN = "AAAAAAAAAA";
    private static final String UPDATED_PAN = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BILL_CREDIT_LIMIT = new BigDecimal(1);
    private static final BigDecimal UPDATED_BILL_CREDIT_LIMIT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CREDIT_LIMIT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CREDIT_LIMIT = new BigDecimal(2);

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ACTIVE;

    @Autowired
    private CustomerBillingInfoRepository customerBillingInfoRepository;

    @Autowired
    private CustomerBillingInfoMapper customerBillingInfoMapper;

    @Autowired
    private CustomerBillingInfoService customerBillingInfoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCustomerBillingInfoMockMvc;

    private CustomerBillingInfo customerBillingInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerBillingInfo createEntity(EntityManager em) {
        CustomerBillingInfo customerBillingInfo = new CustomerBillingInfo()
            .tin(DEFAULT_TIN)
            .gst(DEFAULT_GST)
            .pan(DEFAULT_PAN)
            .billCreditLimit(DEFAULT_BILL_CREDIT_LIMIT)
            .creditLimit(DEFAULT_CREDIT_LIMIT)
            .status(DEFAULT_STATUS);
        return customerBillingInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerBillingInfo createUpdatedEntity(EntityManager em) {
        CustomerBillingInfo customerBillingInfo = new CustomerBillingInfo()
            .tin(UPDATED_TIN)
            .gst(UPDATED_GST)
            .pan(UPDATED_PAN)
            .billCreditLimit(UPDATED_BILL_CREDIT_LIMIT)
            .creditLimit(UPDATED_CREDIT_LIMIT)
            .status(UPDATED_STATUS);
        return customerBillingInfo;
    }

    @BeforeEach
    public void initTest() {
        customerBillingInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomerBillingInfo() throws Exception {
        int databaseSizeBeforeCreate = customerBillingInfoRepository.findAll().size();
        // Create the CustomerBillingInfo
        CustomerBillingInfoDTO customerBillingInfoDTO = customerBillingInfoMapper.toDto(customerBillingInfo);
        restCustomerBillingInfoMockMvc.perform(post("/api/customer-billing-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerBillingInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the CustomerBillingInfo in the database
        List<CustomerBillingInfo> customerBillingInfoList = customerBillingInfoRepository.findAll();
        assertThat(customerBillingInfoList).hasSize(databaseSizeBeforeCreate + 1);
        CustomerBillingInfo testCustomerBillingInfo = customerBillingInfoList.get(customerBillingInfoList.size() - 1);
        assertThat(testCustomerBillingInfo.getTin()).isEqualTo(DEFAULT_TIN);
        assertThat(testCustomerBillingInfo.getGst()).isEqualTo(DEFAULT_GST);
        assertThat(testCustomerBillingInfo.getPan()).isEqualTo(DEFAULT_PAN);
        assertThat(testCustomerBillingInfo.getBillCreditLimit()).isEqualTo(DEFAULT_BILL_CREDIT_LIMIT);
        assertThat(testCustomerBillingInfo.getCreditLimit()).isEqualTo(DEFAULT_CREDIT_LIMIT);
        assertThat(testCustomerBillingInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createCustomerBillingInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerBillingInfoRepository.findAll().size();

        // Create the CustomerBillingInfo with an existing ID
        customerBillingInfo.setId(1L);
        CustomerBillingInfoDTO customerBillingInfoDTO = customerBillingInfoMapper.toDto(customerBillingInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerBillingInfoMockMvc.perform(post("/api/customer-billing-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerBillingInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerBillingInfo in the database
        List<CustomerBillingInfo> customerBillingInfoList = customerBillingInfoRepository.findAll();
        assertThat(customerBillingInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCustomerBillingInfos() throws Exception {
        // Initialize the database
        customerBillingInfoRepository.saveAndFlush(customerBillingInfo);

        // Get all the customerBillingInfoList
        restCustomerBillingInfoMockMvc.perform(get("/api/customer-billing-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerBillingInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].tin").value(hasItem(DEFAULT_TIN)))
            .andExpect(jsonPath("$.[*].gst").value(hasItem(DEFAULT_GST)))
            .andExpect(jsonPath("$.[*].pan").value(hasItem(DEFAULT_PAN)))
            .andExpect(jsonPath("$.[*].billCreditLimit").value(hasItem(DEFAULT_BILL_CREDIT_LIMIT.intValue())))
            .andExpect(jsonPath("$.[*].creditLimit").value(hasItem(DEFAULT_CREDIT_LIMIT.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getCustomerBillingInfo() throws Exception {
        // Initialize the database
        customerBillingInfoRepository.saveAndFlush(customerBillingInfo);

        // Get the customerBillingInfo
        restCustomerBillingInfoMockMvc.perform(get("/api/customer-billing-infos/{id}", customerBillingInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(customerBillingInfo.getId().intValue()))
            .andExpect(jsonPath("$.tin").value(DEFAULT_TIN))
            .andExpect(jsonPath("$.gst").value(DEFAULT_GST))
            .andExpect(jsonPath("$.pan").value(DEFAULT_PAN))
            .andExpect(jsonPath("$.billCreditLimit").value(DEFAULT_BILL_CREDIT_LIMIT.intValue()))
            .andExpect(jsonPath("$.creditLimit").value(DEFAULT_CREDIT_LIMIT.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCustomerBillingInfo() throws Exception {
        // Get the customerBillingInfo
        restCustomerBillingInfoMockMvc.perform(get("/api/customer-billing-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomerBillingInfo() throws Exception {
        // Initialize the database
        customerBillingInfoRepository.saveAndFlush(customerBillingInfo);

        int databaseSizeBeforeUpdate = customerBillingInfoRepository.findAll().size();

        // Update the customerBillingInfo
        CustomerBillingInfo updatedCustomerBillingInfo = customerBillingInfoRepository.findById(customerBillingInfo.getId()).get();
        // Disconnect from session so that the updates on updatedCustomerBillingInfo are not directly saved in db
        em.detach(updatedCustomerBillingInfo);
        updatedCustomerBillingInfo
            .tin(UPDATED_TIN)
            .gst(UPDATED_GST)
            .pan(UPDATED_PAN)
            .billCreditLimit(UPDATED_BILL_CREDIT_LIMIT)
            .creditLimit(UPDATED_CREDIT_LIMIT)
            .status(UPDATED_STATUS);
        CustomerBillingInfoDTO customerBillingInfoDTO = customerBillingInfoMapper.toDto(updatedCustomerBillingInfo);

        restCustomerBillingInfoMockMvc.perform(put("/api/customer-billing-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerBillingInfoDTO)))
            .andExpect(status().isOk());

        // Validate the CustomerBillingInfo in the database
        List<CustomerBillingInfo> customerBillingInfoList = customerBillingInfoRepository.findAll();
        assertThat(customerBillingInfoList).hasSize(databaseSizeBeforeUpdate);
        CustomerBillingInfo testCustomerBillingInfo = customerBillingInfoList.get(customerBillingInfoList.size() - 1);
        assertThat(testCustomerBillingInfo.getTin()).isEqualTo(UPDATED_TIN);
        assertThat(testCustomerBillingInfo.getGst()).isEqualTo(UPDATED_GST);
        assertThat(testCustomerBillingInfo.getPan()).isEqualTo(UPDATED_PAN);
        assertThat(testCustomerBillingInfo.getBillCreditLimit()).isEqualTo(UPDATED_BILL_CREDIT_LIMIT);
        assertThat(testCustomerBillingInfo.getCreditLimit()).isEqualTo(UPDATED_CREDIT_LIMIT);
        assertThat(testCustomerBillingInfo.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomerBillingInfo() throws Exception {
        int databaseSizeBeforeUpdate = customerBillingInfoRepository.findAll().size();

        // Create the CustomerBillingInfo
        CustomerBillingInfoDTO customerBillingInfoDTO = customerBillingInfoMapper.toDto(customerBillingInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerBillingInfoMockMvc.perform(put("/api/customer-billing-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerBillingInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerBillingInfo in the database
        List<CustomerBillingInfo> customerBillingInfoList = customerBillingInfoRepository.findAll();
        assertThat(customerBillingInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCustomerBillingInfo() throws Exception {
        // Initialize the database
        customerBillingInfoRepository.saveAndFlush(customerBillingInfo);

        int databaseSizeBeforeDelete = customerBillingInfoRepository.findAll().size();

        // Delete the customerBillingInfo
        restCustomerBillingInfoMockMvc.perform(delete("/api/customer-billing-infos/{id}", customerBillingInfo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CustomerBillingInfo> customerBillingInfoList = customerBillingInfoRepository.findAll();
        assertThat(customerBillingInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
