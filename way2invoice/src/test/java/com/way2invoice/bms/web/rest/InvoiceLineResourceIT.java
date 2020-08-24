package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.Way2InvoiceApp;
import com.way2invoice.bms.domain.InvoiceLine;
import com.way2invoice.bms.repository.InvoiceLineRepository;
import com.way2invoice.bms.service.InvoiceLineService;
import com.way2invoice.bms.service.dto.InvoiceLineDTO;
import com.way2invoice.bms.service.mapper.InvoiceLineMapper;

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

/**
 * Integration tests for the {@link InvoiceLineResource} REST controller.
 */
@SpringBootTest(classes = Way2InvoiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InvoiceLineResourceIT {

    private static final Double DEFAULT_QUANTITY = 1D;
    private static final Double UPDATED_QUANTITY = 2D;

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DISCOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DISCOUNT = new BigDecimal(2);

    private static final Boolean DEFAULT_IS_PERCENTAGE = false;
    private static final Boolean UPDATED_IS_PERCENTAGE = true;

    @Autowired
    private InvoiceLineRepository invoiceLineRepository;

    @Autowired
    private InvoiceLineMapper invoiceLineMapper;

    @Autowired
    private InvoiceLineService invoiceLineService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInvoiceLineMockMvc;

    private InvoiceLine invoiceLine;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceLine createEntity(EntityManager em) {
        InvoiceLine invoiceLine = new InvoiceLine()
            .quantity(DEFAULT_QUANTITY)
            .price(DEFAULT_PRICE)
            .discount(DEFAULT_DISCOUNT)
            .isPercentage(DEFAULT_IS_PERCENTAGE);
        return invoiceLine;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceLine createUpdatedEntity(EntityManager em) {
        InvoiceLine invoiceLine = new InvoiceLine()
            .quantity(UPDATED_QUANTITY)
            .price(UPDATED_PRICE)
            .discount(UPDATED_DISCOUNT)
            .isPercentage(UPDATED_IS_PERCENTAGE);
        return invoiceLine;
    }

    @BeforeEach
    public void initTest() {
        invoiceLine = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvoiceLine() throws Exception {
        int databaseSizeBeforeCreate = invoiceLineRepository.findAll().size();
        // Create the InvoiceLine
        InvoiceLineDTO invoiceLineDTO = invoiceLineMapper.toDto(invoiceLine);
        restInvoiceLineMockMvc.perform(post("/api/invoice-lines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceLineDTO)))
            .andExpect(status().isCreated());

        // Validate the InvoiceLine in the database
        List<InvoiceLine> invoiceLineList = invoiceLineRepository.findAll();
        assertThat(invoiceLineList).hasSize(databaseSizeBeforeCreate + 1);
        InvoiceLine testInvoiceLine = invoiceLineList.get(invoiceLineList.size() - 1);
        assertThat(testInvoiceLine.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testInvoiceLine.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testInvoiceLine.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testInvoiceLine.isIsPercentage()).isEqualTo(DEFAULT_IS_PERCENTAGE);
    }

    @Test
    @Transactional
    public void createInvoiceLineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = invoiceLineRepository.findAll().size();

        // Create the InvoiceLine with an existing ID
        invoiceLine.setId(1L);
        InvoiceLineDTO invoiceLineDTO = invoiceLineMapper.toDto(invoiceLine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvoiceLineMockMvc.perform(post("/api/invoice-lines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceLineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvoiceLine in the database
        List<InvoiceLine> invoiceLineList = invoiceLineRepository.findAll();
        assertThat(invoiceLineList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceLineRepository.findAll().size();
        // set the field null
        invoiceLine.setQuantity(null);

        // Create the InvoiceLine, which fails.
        InvoiceLineDTO invoiceLineDTO = invoiceLineMapper.toDto(invoiceLine);


        restInvoiceLineMockMvc.perform(post("/api/invoice-lines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceLineDTO)))
            .andExpect(status().isBadRequest());

        List<InvoiceLine> invoiceLineList = invoiceLineRepository.findAll();
        assertThat(invoiceLineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceLineRepository.findAll().size();
        // set the field null
        invoiceLine.setPrice(null);

        // Create the InvoiceLine, which fails.
        InvoiceLineDTO invoiceLineDTO = invoiceLineMapper.toDto(invoiceLine);


        restInvoiceLineMockMvc.perform(post("/api/invoice-lines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceLineDTO)))
            .andExpect(status().isBadRequest());

        List<InvoiceLine> invoiceLineList = invoiceLineRepository.findAll();
        assertThat(invoiceLineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInvoiceLines() throws Exception {
        // Initialize the database
        invoiceLineRepository.saveAndFlush(invoiceLine);

        // Get all the invoiceLineList
        restInvoiceLineMockMvc.perform(get("/api/invoice-lines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invoiceLine.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.doubleValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.intValue())))
            .andExpect(jsonPath("$.[*].isPercentage").value(hasItem(DEFAULT_IS_PERCENTAGE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getInvoiceLine() throws Exception {
        // Initialize the database
        invoiceLineRepository.saveAndFlush(invoiceLine);

        // Get the invoiceLine
        restInvoiceLineMockMvc.perform(get("/api/invoice-lines/{id}", invoiceLine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(invoiceLine.getId().intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.doubleValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT.intValue()))
            .andExpect(jsonPath("$.isPercentage").value(DEFAULT_IS_PERCENTAGE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingInvoiceLine() throws Exception {
        // Get the invoiceLine
        restInvoiceLineMockMvc.perform(get("/api/invoice-lines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvoiceLine() throws Exception {
        // Initialize the database
        invoiceLineRepository.saveAndFlush(invoiceLine);

        int databaseSizeBeforeUpdate = invoiceLineRepository.findAll().size();

        // Update the invoiceLine
        InvoiceLine updatedInvoiceLine = invoiceLineRepository.findById(invoiceLine.getId()).get();
        // Disconnect from session so that the updates on updatedInvoiceLine are not directly saved in db
        em.detach(updatedInvoiceLine);
        updatedInvoiceLine
            .quantity(UPDATED_QUANTITY)
            .price(UPDATED_PRICE)
            .discount(UPDATED_DISCOUNT)
            .isPercentage(UPDATED_IS_PERCENTAGE);
        InvoiceLineDTO invoiceLineDTO = invoiceLineMapper.toDto(updatedInvoiceLine);

        restInvoiceLineMockMvc.perform(put("/api/invoice-lines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceLineDTO)))
            .andExpect(status().isOk());

        // Validate the InvoiceLine in the database
        List<InvoiceLine> invoiceLineList = invoiceLineRepository.findAll();
        assertThat(invoiceLineList).hasSize(databaseSizeBeforeUpdate);
        InvoiceLine testInvoiceLine = invoiceLineList.get(invoiceLineList.size() - 1);
        assertThat(testInvoiceLine.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testInvoiceLine.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testInvoiceLine.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testInvoiceLine.isIsPercentage()).isEqualTo(UPDATED_IS_PERCENTAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingInvoiceLine() throws Exception {
        int databaseSizeBeforeUpdate = invoiceLineRepository.findAll().size();

        // Create the InvoiceLine
        InvoiceLineDTO invoiceLineDTO = invoiceLineMapper.toDto(invoiceLine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoiceLineMockMvc.perform(put("/api/invoice-lines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceLineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvoiceLine in the database
        List<InvoiceLine> invoiceLineList = invoiceLineRepository.findAll();
        assertThat(invoiceLineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInvoiceLine() throws Exception {
        // Initialize the database
        invoiceLineRepository.saveAndFlush(invoiceLine);

        int databaseSizeBeforeDelete = invoiceLineRepository.findAll().size();

        // Delete the invoiceLine
        restInvoiceLineMockMvc.perform(delete("/api/invoice-lines/{id}", invoiceLine.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvoiceLine> invoiceLineList = invoiceLineRepository.findAll();
        assertThat(invoiceLineList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
