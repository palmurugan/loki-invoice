package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.Way2InvoiceApp;
import com.way2invoice.bms.domain.CustomerContact;
import com.way2invoice.bms.repository.CustomerContactRepository;
import com.way2invoice.bms.service.CustomerContactService;
import com.way2invoice.bms.service.dto.CustomerContactDTO;
import com.way2invoice.bms.service.mapper.CustomerContactMapper;

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
 * Integration tests for the {@link CustomerContactResource} REST controller.
 */
@SpringBootTest(classes = Way2InvoiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CustomerContactResourceIT {

    private static final String DEFAULT_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_ZIPCODE = "AAAAAAAAAA";
    private static final String UPDATED_ZIPCODE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ACTIVE;

    @Autowired
    private CustomerContactRepository customerContactRepository;

    @Autowired
    private CustomerContactMapper customerContactMapper;

    @Autowired
    private CustomerContactService customerContactService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCustomerContactMockMvc;

    private CustomerContact customerContact;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerContact createEntity(EntityManager em) {
        CustomerContact customerContact = new CustomerContact()
            .address1(DEFAULT_ADDRESS_1)
            .address2(DEFAULT_ADDRESS_2)
            .city(DEFAULT_CITY)
            .state(DEFAULT_STATE)
            .country(DEFAULT_COUNTRY)
            .zipcode(DEFAULT_ZIPCODE)
            .status(DEFAULT_STATUS);
        return customerContact;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerContact createUpdatedEntity(EntityManager em) {
        CustomerContact customerContact = new CustomerContact()
            .address1(UPDATED_ADDRESS_1)
            .address2(UPDATED_ADDRESS_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .zipcode(UPDATED_ZIPCODE)
            .status(UPDATED_STATUS);
        return customerContact;
    }

    @BeforeEach
    public void initTest() {
        customerContact = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomerContact() throws Exception {
        int databaseSizeBeforeCreate = customerContactRepository.findAll().size();
        // Create the CustomerContact
        CustomerContactDTO customerContactDTO = customerContactMapper.toDto(customerContact);
        restCustomerContactMockMvc.perform(post("/api/customer-contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerContactDTO)))
            .andExpect(status().isCreated());

        // Validate the CustomerContact in the database
        List<CustomerContact> customerContactList = customerContactRepository.findAll();
        assertThat(customerContactList).hasSize(databaseSizeBeforeCreate + 1);
        CustomerContact testCustomerContact = customerContactList.get(customerContactList.size() - 1);
        assertThat(testCustomerContact.getAddress1()).isEqualTo(DEFAULT_ADDRESS_1);
        assertThat(testCustomerContact.getAddress2()).isEqualTo(DEFAULT_ADDRESS_2);
        assertThat(testCustomerContact.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testCustomerContact.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testCustomerContact.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testCustomerContact.getZipcode()).isEqualTo(DEFAULT_ZIPCODE);
        assertThat(testCustomerContact.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createCustomerContactWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerContactRepository.findAll().size();

        // Create the CustomerContact with an existing ID
        customerContact.setId(1L);
        CustomerContactDTO customerContactDTO = customerContactMapper.toDto(customerContact);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerContactMockMvc.perform(post("/api/customer-contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerContactDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerContact in the database
        List<CustomerContact> customerContactList = customerContactRepository.findAll();
        assertThat(customerContactList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAddress1IsRequired() throws Exception {
        int databaseSizeBeforeTest = customerContactRepository.findAll().size();
        // set the field null
        customerContact.setAddress1(null);

        // Create the CustomerContact, which fails.
        CustomerContactDTO customerContactDTO = customerContactMapper.toDto(customerContact);


        restCustomerContactMockMvc.perform(post("/api/customer-contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerContactDTO)))
            .andExpect(status().isBadRequest());

        List<CustomerContact> customerContactList = customerContactRepository.findAll();
        assertThat(customerContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerContactRepository.findAll().size();
        // set the field null
        customerContact.setCity(null);

        // Create the CustomerContact, which fails.
        CustomerContactDTO customerContactDTO = customerContactMapper.toDto(customerContact);


        restCustomerContactMockMvc.perform(post("/api/customer-contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerContactDTO)))
            .andExpect(status().isBadRequest());

        List<CustomerContact> customerContactList = customerContactRepository.findAll();
        assertThat(customerContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerContactRepository.findAll().size();
        // set the field null
        customerContact.setState(null);

        // Create the CustomerContact, which fails.
        CustomerContactDTO customerContactDTO = customerContactMapper.toDto(customerContact);


        restCustomerContactMockMvc.perform(post("/api/customer-contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerContactDTO)))
            .andExpect(status().isBadRequest());

        List<CustomerContact> customerContactList = customerContactRepository.findAll();
        assertThat(customerContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCountryIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerContactRepository.findAll().size();
        // set the field null
        customerContact.setCountry(null);

        // Create the CustomerContact, which fails.
        CustomerContactDTO customerContactDTO = customerContactMapper.toDto(customerContact);


        restCustomerContactMockMvc.perform(post("/api/customer-contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerContactDTO)))
            .andExpect(status().isBadRequest());

        List<CustomerContact> customerContactList = customerContactRepository.findAll();
        assertThat(customerContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkZipcodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerContactRepository.findAll().size();
        // set the field null
        customerContact.setZipcode(null);

        // Create the CustomerContact, which fails.
        CustomerContactDTO customerContactDTO = customerContactMapper.toDto(customerContact);


        restCustomerContactMockMvc.perform(post("/api/customer-contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerContactDTO)))
            .andExpect(status().isBadRequest());

        List<CustomerContact> customerContactList = customerContactRepository.findAll();
        assertThat(customerContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCustomerContacts() throws Exception {
        // Initialize the database
        customerContactRepository.saveAndFlush(customerContact);

        // Get all the customerContactList
        restCustomerContactMockMvc.perform(get("/api/customer-contacts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerContact.getId().intValue())))
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
    public void getCustomerContact() throws Exception {
        // Initialize the database
        customerContactRepository.saveAndFlush(customerContact);

        // Get the customerContact
        restCustomerContactMockMvc.perform(get("/api/customer-contacts/{id}", customerContact.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(customerContact.getId().intValue()))
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
    public void getNonExistingCustomerContact() throws Exception {
        // Get the customerContact
        restCustomerContactMockMvc.perform(get("/api/customer-contacts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomerContact() throws Exception {
        // Initialize the database
        customerContactRepository.saveAndFlush(customerContact);

        int databaseSizeBeforeUpdate = customerContactRepository.findAll().size();

        // Update the customerContact
        CustomerContact updatedCustomerContact = customerContactRepository.findById(customerContact.getId()).get();
        // Disconnect from session so that the updates on updatedCustomerContact are not directly saved in db
        em.detach(updatedCustomerContact);
        updatedCustomerContact
            .address1(UPDATED_ADDRESS_1)
            .address2(UPDATED_ADDRESS_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .zipcode(UPDATED_ZIPCODE)
            .status(UPDATED_STATUS);
        CustomerContactDTO customerContactDTO = customerContactMapper.toDto(updatedCustomerContact);

        restCustomerContactMockMvc.perform(put("/api/customer-contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerContactDTO)))
            .andExpect(status().isOk());

        // Validate the CustomerContact in the database
        List<CustomerContact> customerContactList = customerContactRepository.findAll();
        assertThat(customerContactList).hasSize(databaseSizeBeforeUpdate);
        CustomerContact testCustomerContact = customerContactList.get(customerContactList.size() - 1);
        assertThat(testCustomerContact.getAddress1()).isEqualTo(UPDATED_ADDRESS_1);
        assertThat(testCustomerContact.getAddress2()).isEqualTo(UPDATED_ADDRESS_2);
        assertThat(testCustomerContact.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testCustomerContact.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testCustomerContact.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testCustomerContact.getZipcode()).isEqualTo(UPDATED_ZIPCODE);
        assertThat(testCustomerContact.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomerContact() throws Exception {
        int databaseSizeBeforeUpdate = customerContactRepository.findAll().size();

        // Create the CustomerContact
        CustomerContactDTO customerContactDTO = customerContactMapper.toDto(customerContact);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerContactMockMvc.perform(put("/api/customer-contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerContactDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerContact in the database
        List<CustomerContact> customerContactList = customerContactRepository.findAll();
        assertThat(customerContactList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCustomerContact() throws Exception {
        // Initialize the database
        customerContactRepository.saveAndFlush(customerContact);

        int databaseSizeBeforeDelete = customerContactRepository.findAll().size();

        // Delete the customerContact
        restCustomerContactMockMvc.perform(delete("/api/customer-contacts/{id}", customerContact.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CustomerContact> customerContactList = customerContactRepository.findAll();
        assertThat(customerContactList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
