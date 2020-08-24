package com.way2invoice.bms.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.way2invoice.bms.Way2InvoiceApp;
import com.way2invoice.bms.domain.Customer;
import com.way2invoice.bms.domain.CustomerBillingInfo;
import com.way2invoice.bms.domain.CustomerCategory;
import com.way2invoice.bms.domain.enumeration.Status;
import com.way2invoice.bms.repository.CustomerCategoryRepository;
import com.way2invoice.bms.repository.CustomerRepository;
import com.way2invoice.bms.service.CustomerService;
import com.way2invoice.bms.service.dto.CustomerDTO;
import com.way2invoice.bms.service.mapper.CustomerMapper;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CustomerResource} REST controller.
 */
@SpringBootTest(classes = Way2InvoiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CustomerResourceIT {

  private static final Long DEFAULT_CLIENT_ID = 1L;
  private static final Long UPDATED_CLIENT_ID = 2L;

  private static final String DEFAULT_NAME = "AAAAAAAAAA";
  private static final String UPDATED_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_COMPANY = "AAAAAAAAAA";
  private static final String UPDATED_COMPANY = "BBBBBBBBBB";

  private static final Status DEFAULT_STATUS = Status.ACTIVE;
  private static final Status UPDATED_STATUS = Status.ACTIVE;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private CustomerCategoryRepository customerCategoryRepository;

  @Autowired
  private CustomerMapper customerMapper;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restCustomerMockMvc;

  private Customer customer;

  /**
   * Create an entity for this test.
   * <p>
   * This is a static method, as tests for other entities might also need it, if they test an entity
   * which requires the current entity.
   */
  public static Customer createEntity(EntityManager em) {
    Customer customer = new Customer()
        .clientId(DEFAULT_CLIENT_ID)
        .name(DEFAULT_NAME)
        .company(DEFAULT_COMPANY);
    return customer;
  }

  /**
   * Create an updated entity for this test.
   * <p>
   * This is a static method, as tests for other entities might also need it, if they test an entity
   * which requires the current entity.
   */
  public static Customer createUpdatedEntity(EntityManager em) {
    CustomerBillingInfo customerBillingInfo = CustomerBillingInfoResourceIT.createEntity(em);
    Customer customer = new Customer()
        .clientId(UPDATED_CLIENT_ID)
        .customerBillingInfo(customerBillingInfo)
        .name(UPDATED_NAME)
        .company(UPDATED_COMPANY);
    return customer;
  }

  @BeforeEach
  public void initTest() {
    customer = createEntity(em);
  }

  @Test
  @Transactional
  public void createCustomer() throws Exception {
    // Persist Customer Category
    CustomerCategory customerCategory = CustomerCategoryResourceIT.createEntity(em);
    customerCategory = customerCategoryRepository.save(customerCategory);

    int databaseSizeBeforeCreate = customerRepository.findAll().size();
    // Create the Customer
    customer.setCustomerCategory(customerCategory);
    CustomerDTO customerDTO = customerMapper.toDto(customer);
    restCustomerMockMvc.perform(post("/api/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(TestUtil.convertObjectToJsonBytes(customerDTO)))
        .andExpect(status().isCreated());

    // Validate the Customer in the database
    List<Customer> customerList = customerRepository.findAll();
    assertThat(customerList).hasSize(databaseSizeBeforeCreate + 1);
    Customer testCustomer = customerList.get(customerList.size() - 1);
    assertThat(testCustomer.getName()).isEqualTo(DEFAULT_NAME);
    assertThat(testCustomer.getCompany()).isEqualTo(DEFAULT_COMPANY);
    assertThat(testCustomer.getStatus()).isEqualTo(DEFAULT_STATUS);
  }

  @Test
  @Transactional
  public void createCustomerWithExistingId() throws Exception {
    int databaseSizeBeforeCreate = customerRepository.findAll().size();

    // Create the Customer with an existing ID
    customer.setId(1L);
    CustomerDTO customerDTO = customerMapper.toDto(customer);

    // An entity with an existing ID cannot be created, so this API call must fail
    restCustomerMockMvc.perform(post("/api/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(TestUtil.convertObjectToJsonBytes(customerDTO)))
        .andExpect(status().isBadRequest());

    // Validate the Customer in the database
    List<Customer> customerList = customerRepository.findAll();
    assertThat(customerList).hasSize(databaseSizeBeforeCreate);
  }


  @Test
  @Transactional
  public void checkClientIdIsRequired() throws Exception {
    int databaseSizeBeforeTest = customerRepository.findAll().size();
    // set the field null
    customer.setClientId(null);

    // Create the Customer, which fails.
    CustomerDTO customerDTO = customerMapper.toDto(customer);

    restCustomerMockMvc.perform(post("/api/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(TestUtil.convertObjectToJsonBytes(customerDTO)))
        .andExpect(status().isBadRequest());

    List<Customer> customerList = customerRepository.findAll();
    assertThat(customerList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  public void checkNameIsRequired() throws Exception {
    int databaseSizeBeforeTest = customerRepository.findAll().size();
    // set the field null
    customer.setName(null);

    // Create the Customer, which fails.
    CustomerDTO customerDTO = customerMapper.toDto(customer);

    restCustomerMockMvc.perform(post("/api/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(TestUtil.convertObjectToJsonBytes(customerDTO)))
        .andExpect(status().isBadRequest());

    List<Customer> customerList = customerRepository.findAll();
    assertThat(customerList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  public void checkCompanyIsRequired() throws Exception {
    int databaseSizeBeforeTest = customerRepository.findAll().size();
    // set the field null
    customer.setCompany(null);

    // Create the Customer, which fails.
    CustomerDTO customerDTO = customerMapper.toDto(customer);

    restCustomerMockMvc.perform(post("/api/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(TestUtil.convertObjectToJsonBytes(customerDTO)))
        .andExpect(status().isBadRequest());

    List<Customer> customerList = customerRepository.findAll();
    assertThat(customerList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  public void checkStatusIsRequired() throws Exception {
    int databaseSizeBeforeTest = customerRepository.findAll().size();
    // set the field null
    customer.setStatus(null);

    // Create the Customer, which fails.
    CustomerDTO customerDTO = customerMapper.toDto(customer);

    restCustomerMockMvc.perform(post("/api/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(TestUtil.convertObjectToJsonBytes(customerDTO)))
        .andExpect(status().isBadRequest());

    List<Customer> customerList = customerRepository.findAll();
    assertThat(customerList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  public void getAllCustomers() throws Exception {
    // Initialize the database
    customerRepository.saveAndFlush(customer);

    // Get all the customerList
    restCustomerMockMvc.perform(get("/api/customers?sort=id,desc"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.[*].id").value(hasItem(customer.getId().intValue())))
        .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
        .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY)))
        .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
  }

  @Test
  @Transactional
  public void getCustomer() throws Exception {
    // Persist Customer Category
    CustomerCategory customerCategory = CustomerCategoryResourceIT.createEntity(em);
    customerCategory = customerCategoryRepository.save(customerCategory);

    // Initialize the database
    customer.customerCategory(customerCategory);
    customerRepository.saveAndFlush(customer);

    // Get the customer
    restCustomerMockMvc.perform(get("/api/customers/{id}", customer.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.id").value(customer.getId().intValue()))
        .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
        .andExpect(jsonPath("$.company").value(DEFAULT_COMPANY))
        .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
  }

  @Test
  @Transactional
  public void getNonExistingCustomer() throws Exception {
    // Get the customer
    restCustomerMockMvc.perform(get("/api/customers/{id}", Long.MAX_VALUE))
        .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  public void updateCustomer() throws Exception {
    // Persist Customer Category
    CustomerCategory customerCategory = CustomerCategoryResourceIT.createEntity(em);
    customerCategory = customerCategoryRepository.save(customerCategory);

    // Initialize the database
    customer.customerCategory(customerCategory);
    customerRepository.saveAndFlush(customer);

    int databaseSizeBeforeUpdate = customerRepository.findAll().size();

    // Update the customer
    Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
    // Disconnect from session so that the updates on updatedCustomer are not directly saved in db
    em.detach(updatedCustomer);
    updatedCustomer
        .clientId(UPDATED_CLIENT_ID)
        .name(UPDATED_NAME)
        .company(UPDATED_COMPANY);
    CustomerDTO customerDTO = customerMapper.toDto(updatedCustomer);

    restCustomerMockMvc.perform(put("/api/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(TestUtil.convertObjectToJsonBytes(customerDTO)))
        .andExpect(status().isOk());

    // Validate the Customer in the database
    List<Customer> customerList = customerRepository.findAll();
    assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
    Customer testCustomer = customerList.get(customerList.size() - 1);
    assertThat(testCustomer.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testCustomer.getCompany()).isEqualTo(UPDATED_COMPANY);
    assertThat(testCustomer.getStatus()).isEqualTo(UPDATED_STATUS);
  }

  @Test
  @Transactional
  public void updateNonExistingCustomer() throws Exception {
    int databaseSizeBeforeUpdate = customerRepository.findAll().size();

    // Create the Customer
    CustomerDTO customerDTO = customerMapper.toDto(customer);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restCustomerMockMvc.perform(put("/api/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(TestUtil.convertObjectToJsonBytes(customerDTO)))
        .andExpect(status().isBadRequest());

    // Validate the Customer in the database
    List<Customer> customerList = customerRepository.findAll();
    assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  public void deleteCustomer() throws Exception {
    // Initialize the database
    customerRepository.saveAndFlush(customer);

    int databaseSizeBeforeDelete = customerRepository.findAll().size();

    // Delete the customer
    restCustomerMockMvc.perform(delete("/api/customers/{id}", customer.getId())
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<Customer> customerList = customerRepository.findAll();
    assertThat(customerList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
