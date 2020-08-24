package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.Way2InvoiceApp;
import com.way2invoice.bms.domain.InvoicePayment;
import com.way2invoice.bms.repository.InvoicePaymentRepository;
import com.way2invoice.bms.service.InvoicePaymentService;
import com.way2invoice.bms.service.dto.InvoicePaymentDTO;
import com.way2invoice.bms.service.mapper.InvoicePaymentMapper;

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
 * Integration tests for the {@link InvoicePaymentResource} REST controller.
 */
@SpringBootTest(classes = Way2InvoiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InvoicePaymentResourceIT {

    private static final Long DEFAULT_INVOICE_ID = 1L;
    private static final Long UPDATED_INVOICE_ID = 2L;

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.DEACTIVE;

    @Autowired
    private InvoicePaymentRepository invoicePaymentRepository;

    @Autowired
    private InvoicePaymentMapper invoicePaymentMapper;

    @Autowired
    private InvoicePaymentService invoicePaymentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInvoicePaymentMockMvc;

    private InvoicePayment invoicePayment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoicePayment createEntity(EntityManager em) {
        InvoicePayment invoicePayment = new InvoicePayment();
        return invoicePayment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoicePayment createUpdatedEntity(EntityManager em) {
        InvoicePayment invoicePayment = new InvoicePayment();
        return invoicePayment;
    }

    @BeforeEach
    public void initTest() {
        invoicePayment = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvoicePayment() throws Exception {
        int databaseSizeBeforeCreate = invoicePaymentRepository.findAll().size();
        // Create the InvoicePayment
        InvoicePaymentDTO invoicePaymentDTO = invoicePaymentMapper.toDto(invoicePayment);
        restInvoicePaymentMockMvc.perform(post("/api/invoice-payments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoicePaymentDTO)))
            .andExpect(status().isCreated());

        // Validate the InvoicePayment in the database
        List<InvoicePayment> invoicePaymentList = invoicePaymentRepository.findAll();
        assertThat(invoicePaymentList).hasSize(databaseSizeBeforeCreate + 1);
        InvoicePayment testInvoicePayment = invoicePaymentList.get(invoicePaymentList.size() - 1);
        assertThat(testInvoicePayment.getInvoice().getId()).isEqualTo(DEFAULT_INVOICE_ID);
        assertThat(testInvoicePayment.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createInvoicePaymentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = invoicePaymentRepository.findAll().size();

        // Create the InvoicePayment with an existing ID
        invoicePayment.setId(1L);
        InvoicePaymentDTO invoicePaymentDTO = invoicePaymentMapper.toDto(invoicePayment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvoicePaymentMockMvc.perform(post("/api/invoice-payments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoicePaymentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvoicePayment in the database
        List<InvoicePayment> invoicePaymentList = invoicePaymentRepository.findAll();
        assertThat(invoicePaymentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoicePaymentRepository.findAll().size();
        // set the field null
        invoicePayment.setStatus(null);

        // Create the InvoicePayment, which fails.
        InvoicePaymentDTO invoicePaymentDTO = invoicePaymentMapper.toDto(invoicePayment);


        restInvoicePaymentMockMvc.perform(post("/api/invoice-payments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoicePaymentDTO)))
            .andExpect(status().isBadRequest());

        List<InvoicePayment> invoicePaymentList = invoicePaymentRepository.findAll();
        assertThat(invoicePaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInvoicePayments() throws Exception {
        // Initialize the database
        invoicePaymentRepository.saveAndFlush(invoicePayment);

        // Get all the invoicePaymentList
        restInvoicePaymentMockMvc.perform(get("/api/invoice-payments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invoicePayment.getId().intValue())))
            .andExpect(jsonPath("$.[*].invoiceId").value(hasItem(DEFAULT_INVOICE_ID.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getInvoicePayment() throws Exception {
        // Initialize the database
        invoicePaymentRepository.saveAndFlush(invoicePayment);

        // Get the invoicePayment
        restInvoicePaymentMockMvc.perform(get("/api/invoice-payments/{id}", invoicePayment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(invoicePayment.getId().intValue()))
            .andExpect(jsonPath("$.invoiceId").value(DEFAULT_INVOICE_ID.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingInvoicePayment() throws Exception {
        // Get the invoicePayment
        restInvoicePaymentMockMvc.perform(get("/api/invoice-payments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvoicePayment() throws Exception {
        // Initialize the database
        invoicePaymentRepository.saveAndFlush(invoicePayment);

        int databaseSizeBeforeUpdate = invoicePaymentRepository.findAll().size();

        // Update the invoicePayment
        InvoicePayment updatedInvoicePayment = invoicePaymentRepository.findById(invoicePayment.getId()).get();
        // Disconnect from session so that the updates on updatedInvoicePayment are not directly saved in db
        em.detach(updatedInvoicePayment);
        InvoicePaymentDTO invoicePaymentDTO = invoicePaymentMapper.toDto(updatedInvoicePayment);

        restInvoicePaymentMockMvc.perform(put("/api/invoice-payments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoicePaymentDTO)))
            .andExpect(status().isOk());

        // Validate the InvoicePayment in the database
        List<InvoicePayment> invoicePaymentList = invoicePaymentRepository.findAll();
        assertThat(invoicePaymentList).hasSize(databaseSizeBeforeUpdate);
        InvoicePayment testInvoicePayment = invoicePaymentList.get(invoicePaymentList.size() - 1);
        assertThat(testInvoicePayment.getInvoice().getId()).isEqualTo(UPDATED_INVOICE_ID);
        assertThat(testInvoicePayment.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingInvoicePayment() throws Exception {
        int databaseSizeBeforeUpdate = invoicePaymentRepository.findAll().size();

        // Create the InvoicePayment
        InvoicePaymentDTO invoicePaymentDTO = invoicePaymentMapper.toDto(invoicePayment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoicePaymentMockMvc.perform(put("/api/invoice-payments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoicePaymentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvoicePayment in the database
        List<InvoicePayment> invoicePaymentList = invoicePaymentRepository.findAll();
        assertThat(invoicePaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInvoicePayment() throws Exception {
        // Initialize the database
        invoicePaymentRepository.saveAndFlush(invoicePayment);

        int databaseSizeBeforeDelete = invoicePaymentRepository.findAll().size();

        // Delete the invoicePayment
        restInvoicePaymentMockMvc.perform(delete("/api/invoice-payments/{id}", invoicePayment.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvoicePayment> invoicePaymentList = invoicePaymentRepository.findAll();
        assertThat(invoicePaymentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
