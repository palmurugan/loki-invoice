package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.Way2InvoiceApp;
import com.way2invoice.bms.domain.AccountContact;
import com.way2invoice.bms.repository.AccountContactRepository;
import com.way2invoice.bms.service.AccountContactService;
import com.way2invoice.bms.service.dto.AccountContactDTO;
import com.way2invoice.bms.service.mapper.AccountContactMapper;

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
 * Integration tests for the {@link AccountContactResource} REST controller.
 */
@SpringBootTest(classes = Way2InvoiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AccountContactResourceIT {

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final Long DEFAULT_STATE = 1L;
    private static final Long UPDATED_STATE = 2L;

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_ZIPCODE = "AAAAAAAAAA";
    private static final String UPDATED_ZIPCODE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.DEACTIVE;

    @Autowired
    private AccountContactRepository accountContactRepository;

    @Autowired
    private AccountContactMapper accountContactMapper;

    @Autowired
    private AccountContactService accountContactService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAccountContactMockMvc;

    private AccountContact accountContact;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountContact createEntity(EntityManager em) {
        AccountContact accountContact = new AccountContact()
            .phone(DEFAULT_PHONE)
            .address1(DEFAULT_ADDRESS_1)
            .address2(DEFAULT_ADDRESS_2)
            .city(DEFAULT_CITY)
            .zipcode(DEFAULT_ZIPCODE)
            .status(DEFAULT_STATUS);
        return accountContact;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountContact createUpdatedEntity(EntityManager em) {
        AccountContact accountContact = new AccountContact()
            .phone(UPDATED_PHONE)
            .address1(UPDATED_ADDRESS_1)
            .address2(UPDATED_ADDRESS_2)
            .city(UPDATED_CITY)
            .zipcode(UPDATED_ZIPCODE)
            .status(UPDATED_STATUS);
        return accountContact;
    }

    @BeforeEach
    public void initTest() {
        accountContact = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccountContact() throws Exception {
        int databaseSizeBeforeCreate = accountContactRepository.findAll().size();
        // Create the AccountContact
        AccountContactDTO accountContactDTO = accountContactMapper.toDto(accountContact);
        restAccountContactMockMvc.perform(post("/api/account-contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountContactDTO)))
            .andExpect(status().isCreated());

        // Validate the AccountContact in the database
        List<AccountContact> accountContactList = accountContactRepository.findAll();
        assertThat(accountContactList).hasSize(databaseSizeBeforeCreate + 1);
        AccountContact testAccountContact = accountContactList.get(accountContactList.size() - 1);
        assertThat(testAccountContact.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testAccountContact.getAddress1()).isEqualTo(DEFAULT_ADDRESS_1);
        assertThat(testAccountContact.getAddress2()).isEqualTo(DEFAULT_ADDRESS_2);
        assertThat(testAccountContact.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testAccountContact.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testAccountContact.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testAccountContact.getZipcode()).isEqualTo(DEFAULT_ZIPCODE);
        assertThat(testAccountContact.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createAccountContactWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accountContactRepository.findAll().size();

        // Create the AccountContact with an existing ID
        accountContact.setId(1L);
        AccountContactDTO accountContactDTO = accountContactMapper.toDto(accountContact);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountContactMockMvc.perform(post("/api/account-contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountContactDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountContact in the database
        List<AccountContact> accountContactList = accountContactRepository.findAll();
        assertThat(accountContactList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountContactRepository.findAll().size();
        // set the field null
        accountContact.setPhone(null);

        // Create the AccountContact, which fails.
        AccountContactDTO accountContactDTO = accountContactMapper.toDto(accountContact);


        restAccountContactMockMvc.perform(post("/api/account-contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountContactDTO)))
            .andExpect(status().isBadRequest());

        List<AccountContact> accountContactList = accountContactRepository.findAll();
        assertThat(accountContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAddress1IsRequired() throws Exception {
        int databaseSizeBeforeTest = accountContactRepository.findAll().size();
        // set the field null
        accountContact.setAddress1(null);

        // Create the AccountContact, which fails.
        AccountContactDTO accountContactDTO = accountContactMapper.toDto(accountContact);


        restAccountContactMockMvc.perform(post("/api/account-contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountContactDTO)))
            .andExpect(status().isBadRequest());

        List<AccountContact> accountContactList = accountContactRepository.findAll();
        assertThat(accountContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountContactRepository.findAll().size();
        // set the field null
        accountContact.setCity(null);

        // Create the AccountContact, which fails.
        AccountContactDTO accountContactDTO = accountContactMapper.toDto(accountContact);


        restAccountContactMockMvc.perform(post("/api/account-contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountContactDTO)))
            .andExpect(status().isBadRequest());

        List<AccountContact> accountContactList = accountContactRepository.findAll();
        assertThat(accountContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountContactRepository.findAll().size();
        // set the field null
        accountContact.setState(null);

        // Create the AccountContact, which fails.
        AccountContactDTO accountContactDTO = accountContactMapper.toDto(accountContact);


        restAccountContactMockMvc.perform(post("/api/account-contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountContactDTO)))
            .andExpect(status().isBadRequest());

        List<AccountContact> accountContactList = accountContactRepository.findAll();
        assertThat(accountContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCountryIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountContactRepository.findAll().size();
        // set the field null
        accountContact.setCountry(null);

        // Create the AccountContact, which fails.
        AccountContactDTO accountContactDTO = accountContactMapper.toDto(accountContact);


        restAccountContactMockMvc.perform(post("/api/account-contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountContactDTO)))
            .andExpect(status().isBadRequest());

        List<AccountContact> accountContactList = accountContactRepository.findAll();
        assertThat(accountContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkZipcodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountContactRepository.findAll().size();
        // set the field null
        accountContact.setZipcode(null);

        // Create the AccountContact, which fails.
        AccountContactDTO accountContactDTO = accountContactMapper.toDto(accountContact);


        restAccountContactMockMvc.perform(post("/api/account-contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountContactDTO)))
            .andExpect(status().isBadRequest());

        List<AccountContact> accountContactList = accountContactRepository.findAll();
        assertThat(accountContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountContactRepository.findAll().size();
        // set the field null
        accountContact.setStatus(null);

        // Create the AccountContact, which fails.
        AccountContactDTO accountContactDTO = accountContactMapper.toDto(accountContact);


        restAccountContactMockMvc.perform(post("/api/account-contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountContactDTO)))
            .andExpect(status().isBadRequest());

        List<AccountContact> accountContactList = accountContactRepository.findAll();
        assertThat(accountContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAccountContacts() throws Exception {
        // Initialize the database
        accountContactRepository.saveAndFlush(accountContact);

        // Get all the accountContactList
        restAccountContactMockMvc.perform(get("/api/account-contacts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountContact.getId().intValue())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].address1").value(hasItem(DEFAULT_ADDRESS_1)))
            .andExpect(jsonPath("$.[*].address2").value(hasItem(DEFAULT_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].zipcode").value(hasItem(DEFAULT_ZIPCODE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getAccountContact() throws Exception {
        // Initialize the database
        accountContactRepository.saveAndFlush(accountContact);

        // Get the accountContact
        restAccountContactMockMvc.perform(get("/api/account-contacts/{id}", accountContact.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(accountContact.getId().intValue()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.address1").value(DEFAULT_ADDRESS_1))
            .andExpect(jsonPath("$.address2").value(DEFAULT_ADDRESS_2))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.zipcode").value(DEFAULT_ZIPCODE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAccountContact() throws Exception {
        // Get the accountContact
        restAccountContactMockMvc.perform(get("/api/account-contacts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccountContact() throws Exception {
        // Initialize the database
        accountContactRepository.saveAndFlush(accountContact);

        int databaseSizeBeforeUpdate = accountContactRepository.findAll().size();

        // Update the accountContact
        AccountContact updatedAccountContact = accountContactRepository.findById(accountContact.getId()).get();
        // Disconnect from session so that the updates on updatedAccountContact are not directly saved in db
        em.detach(updatedAccountContact);
        updatedAccountContact
            .phone(UPDATED_PHONE)
            .address1(UPDATED_ADDRESS_1)
            .address2(UPDATED_ADDRESS_2)
            .city(UPDATED_CITY)
            .zipcode(UPDATED_ZIPCODE)
            .status(UPDATED_STATUS);
        AccountContactDTO accountContactDTO = accountContactMapper.toDto(updatedAccountContact);

        restAccountContactMockMvc.perform(put("/api/account-contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountContactDTO)))
            .andExpect(status().isOk());

        // Validate the AccountContact in the database
        List<AccountContact> accountContactList = accountContactRepository.findAll();
        assertThat(accountContactList).hasSize(databaseSizeBeforeUpdate);
        AccountContact testAccountContact = accountContactList.get(accountContactList.size() - 1);
        assertThat(testAccountContact.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testAccountContact.getAddress1()).isEqualTo(UPDATED_ADDRESS_1);
        assertThat(testAccountContact.getAddress2()).isEqualTo(UPDATED_ADDRESS_2);
        assertThat(testAccountContact.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testAccountContact.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testAccountContact.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testAccountContact.getZipcode()).isEqualTo(UPDATED_ZIPCODE);
        assertThat(testAccountContact.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingAccountContact() throws Exception {
        int databaseSizeBeforeUpdate = accountContactRepository.findAll().size();

        // Create the AccountContact
        AccountContactDTO accountContactDTO = accountContactMapper.toDto(accountContact);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountContactMockMvc.perform(put("/api/account-contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountContactDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountContact in the database
        List<AccountContact> accountContactList = accountContactRepository.findAll();
        assertThat(accountContactList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccountContact() throws Exception {
        // Initialize the database
        accountContactRepository.saveAndFlush(accountContact);

        int databaseSizeBeforeDelete = accountContactRepository.findAll().size();

        // Delete the accountContact
        restAccountContactMockMvc.perform(delete("/api/account-contacts/{id}", accountContact.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AccountContact> accountContactList = accountContactRepository.findAll();
        assertThat(accountContactList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
