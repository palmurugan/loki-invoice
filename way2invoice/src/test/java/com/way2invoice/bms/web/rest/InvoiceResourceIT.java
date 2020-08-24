package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.Way2InvoiceApp;
import com.way2invoice.bms.domain.Invoice;
import com.way2invoice.bms.repository.InvoiceRepository;
import com.way2invoice.bms.service.InvoiceService;
import com.way2invoice.bms.service.dto.InvoiceDTO;
import com.way2invoice.bms.service.mapper.InvoiceMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.way2invoice.bms.domain.enumeration.Status;
/**
 * Integration tests for the {@link InvoiceResource} REST controller.
 */
@SpringBootTest(classes = Way2InvoiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InvoiceResourceIT {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DUE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DUE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_TOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BALANCE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CARRIED_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_CARRIED_BALANCE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DISCOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DISCOUNT = new BigDecimal(2);

    private static final Boolean DEFAULT_IS_PERCENTAGE = false;
    private static final Boolean UPDATED_IS_PERCENTAGE = true;

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.DEACTIVE;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInvoiceMockMvc;

    private Invoice invoice;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Invoice createEntity(EntityManager em) {
        Invoice invoice = new Invoice()
            .date(DEFAULT_DATE)
            .dueDate(DEFAULT_DUE_DATE)
            .total(DEFAULT_TOTAL)
            .balance(DEFAULT_BALANCE)
            .carriedBalance(DEFAULT_CARRIED_BALANCE)
            .discount(DEFAULT_DISCOUNT)
            .isPercentage(DEFAULT_IS_PERCENTAGE)
            .notes(DEFAULT_NOTES);
        return invoice;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Invoice createUpdatedEntity(EntityManager em) {
        Invoice invoice = new Invoice()
            .date(UPDATED_DATE)
            .dueDate(UPDATED_DUE_DATE)
            .total(UPDATED_TOTAL)
            .balance(UPDATED_BALANCE)
            .carriedBalance(UPDATED_CARRIED_BALANCE)
            .discount(UPDATED_DISCOUNT)
            .isPercentage(UPDATED_IS_PERCENTAGE)
            .notes(UPDATED_NOTES);
        return invoice;
    }

    @BeforeEach
    public void initTest() {
        invoice = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvoice() throws Exception {
        int databaseSizeBeforeCreate = invoiceRepository.findAll().size();
        // Create the Invoice
        InvoiceDTO invoiceDTO = invoiceMapper.toDto(invoice);
        restInvoiceMockMvc.perform(post("/api/invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceDTO)))
            .andExpect(status().isCreated());

        // Validate the Invoice in the database
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeCreate + 1);
        Invoice testInvoice = invoiceList.get(invoiceList.size() - 1);
        assertThat(testInvoice.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testInvoice.getDueDate()).isEqualTo(DEFAULT_DUE_DATE);
        assertThat(testInvoice.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testInvoice.getBalance()).isEqualTo(DEFAULT_BALANCE);
        assertThat(testInvoice.getCarriedBalance()).isEqualTo(DEFAULT_CARRIED_BALANCE);
        assertThat(testInvoice.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testInvoice.isIsPercentage()).isEqualTo(DEFAULT_IS_PERCENTAGE);
        assertThat(testInvoice.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testInvoice.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createInvoiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = invoiceRepository.findAll().size();

        // Create the Invoice with an existing ID
        invoice.setId(1L);
        InvoiceDTO invoiceDTO = invoiceMapper.toDto(invoice);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvoiceMockMvc.perform(post("/api/invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Invoice in the database
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceRepository.findAll().size();
        // set the field null
        invoice.setDate(null);

        // Create the Invoice, which fails.
        InvoiceDTO invoiceDTO = invoiceMapper.toDto(invoice);


        restInvoiceMockMvc.perform(post("/api/invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceDTO)))
            .andExpect(status().isBadRequest());

        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDueDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceRepository.findAll().size();
        // set the field null
        invoice.setDueDate(null);

        // Create the Invoice, which fails.
        InvoiceDTO invoiceDTO = invoiceMapper.toDto(invoice);


        restInvoiceMockMvc.perform(post("/api/invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceDTO)))
            .andExpect(status().isBadRequest());

        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceRepository.findAll().size();
        // set the field null
        invoice.setTotal(null);

        // Create the Invoice, which fails.
        InvoiceDTO invoiceDTO = invoiceMapper.toDto(invoice);


        restInvoiceMockMvc.perform(post("/api/invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceDTO)))
            .andExpect(status().isBadRequest());

        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceRepository.findAll().size();
        // set the field null
        invoice.setStatus(null);

        // Create the Invoice, which fails.
        InvoiceDTO invoiceDTO = invoiceMapper.toDto(invoice);


        restInvoiceMockMvc.perform(post("/api/invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceDTO)))
            .andExpect(status().isBadRequest());

        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInvoices() throws Exception {
        // Initialize the database
        invoiceRepository.saveAndFlush(invoice);

        // Get all the invoiceList
        restInvoiceMockMvc.perform(get("/api/invoices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invoice.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].dueDate").value(hasItem(DEFAULT_DUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].balance").value(hasItem(DEFAULT_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].carriedBalance").value(hasItem(DEFAULT_CARRIED_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.intValue())))
            .andExpect(jsonPath("$.[*].isPercentage").value(hasItem(DEFAULT_IS_PERCENTAGE.booleanValue())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getInvoice() throws Exception {
        // Initialize the database
        invoiceRepository.saveAndFlush(invoice);

        // Get the invoice
        restInvoiceMockMvc.perform(get("/api/invoices/{id}", invoice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(invoice.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.dueDate").value(DEFAULT_DUE_DATE.toString()))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.intValue()))
            .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE.intValue()))
            .andExpect(jsonPath("$.carriedBalance").value(DEFAULT_CARRIED_BALANCE.intValue()))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT.intValue()))
            .andExpect(jsonPath("$.isPercentage").value(DEFAULT_IS_PERCENTAGE.booleanValue()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingInvoice() throws Exception {
        // Get the invoice
        restInvoiceMockMvc.perform(get("/api/invoices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvoice() throws Exception {
        // Initialize the database
        invoiceRepository.saveAndFlush(invoice);

        int databaseSizeBeforeUpdate = invoiceRepository.findAll().size();

        // Update the invoice
        Invoice updatedInvoice = invoiceRepository.findById(invoice.getId()).get();
        // Disconnect from session so that the updates on updatedInvoice are not directly saved in db
        em.detach(updatedInvoice);
        updatedInvoice
            .date(UPDATED_DATE)
            .dueDate(UPDATED_DUE_DATE)
            .total(UPDATED_TOTAL)
            .balance(UPDATED_BALANCE)
            .carriedBalance(UPDATED_CARRIED_BALANCE)
            .discount(UPDATED_DISCOUNT)
            .isPercentage(UPDATED_IS_PERCENTAGE)
            .notes(UPDATED_NOTES);
        InvoiceDTO invoiceDTO = invoiceMapper.toDto(updatedInvoice);

        restInvoiceMockMvc.perform(put("/api/invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceDTO)))
            .andExpect(status().isOk());

        // Validate the Invoice in the database
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeUpdate);
        Invoice testInvoice = invoiceList.get(invoiceList.size() - 1);
        assertThat(testInvoice.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testInvoice.getDueDate()).isEqualTo(UPDATED_DUE_DATE);
        assertThat(testInvoice.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testInvoice.getBalance()).isEqualTo(UPDATED_BALANCE);
        assertThat(testInvoice.getCarriedBalance()).isEqualTo(UPDATED_CARRIED_BALANCE);
        assertThat(testInvoice.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testInvoice.isIsPercentage()).isEqualTo(UPDATED_IS_PERCENTAGE);
        assertThat(testInvoice.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testInvoice.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingInvoice() throws Exception {
        int databaseSizeBeforeUpdate = invoiceRepository.findAll().size();

        // Create the Invoice
        InvoiceDTO invoiceDTO = invoiceMapper.toDto(invoice);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoiceMockMvc.perform(put("/api/invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Invoice in the database
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInvoice() throws Exception {
        // Initialize the database
        invoiceRepository.saveAndFlush(invoice);

        int databaseSizeBeforeDelete = invoiceRepository.findAll().size();

        // Delete the invoice
        restInvoiceMockMvc.perform(delete("/api/invoices/{id}", invoice.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
