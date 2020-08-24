package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.Way2InvoiceApp;
import com.way2invoice.bms.domain.ItemPrice;
import com.way2invoice.bms.repository.ItemPriceRepository;
import com.way2invoice.bms.service.ItemPriceService;
import com.way2invoice.bms.service.dto.ItemPriceDTO;
import com.way2invoice.bms.service.mapper.ItemPriceMapper;

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
 * Integration tests for the {@link ItemPriceResource} REST controller.
 */
@SpringBootTest(classes = Way2InvoiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ItemPriceResourceIT {

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(2);

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.DEACTIVE;

    @Autowired
    private ItemPriceRepository itemPriceRepository;

    @Autowired
    private ItemPriceMapper itemPriceMapper;

    @Autowired
    private ItemPriceService itemPriceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemPriceMockMvc;

    private ItemPrice itemPrice;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemPrice createEntity(EntityManager em) {
        ItemPrice itemPrice = new ItemPrice()
            .price(DEFAULT_PRICE);
        return itemPrice;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemPrice createUpdatedEntity(EntityManager em) {
        ItemPrice itemPrice = new ItemPrice()
            .price(UPDATED_PRICE);
        return itemPrice;
    }

    @BeforeEach
    public void initTest() {
        itemPrice = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemPrice() throws Exception {
        int databaseSizeBeforeCreate = itemPriceRepository.findAll().size();
        // Create the ItemPrice
        ItemPriceDTO itemPriceDTO = itemPriceMapper.toDto(itemPrice);
        restItemPriceMockMvc.perform(post("/api/item-prices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemPriceDTO)))
            .andExpect(status().isCreated());

        // Validate the ItemPrice in the database
        List<ItemPrice> itemPriceList = itemPriceRepository.findAll();
        assertThat(itemPriceList).hasSize(databaseSizeBeforeCreate + 1);
        ItemPrice testItemPrice = itemPriceList.get(itemPriceList.size() - 1);
        assertThat(testItemPrice.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testItemPrice.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createItemPriceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemPriceRepository.findAll().size();

        // Create the ItemPrice with an existing ID
        itemPrice.setId(1L);
        ItemPriceDTO itemPriceDTO = itemPriceMapper.toDto(itemPrice);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemPriceMockMvc.perform(post("/api/item-prices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemPriceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ItemPrice in the database
        List<ItemPrice> itemPriceList = itemPriceRepository.findAll();
        assertThat(itemPriceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemPriceRepository.findAll().size();
        // set the field null
        itemPrice.setPrice(null);

        // Create the ItemPrice, which fails.
        ItemPriceDTO itemPriceDTO = itemPriceMapper.toDto(itemPrice);


        restItemPriceMockMvc.perform(post("/api/item-prices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemPriceDTO)))
            .andExpect(status().isBadRequest());

        List<ItemPrice> itemPriceList = itemPriceRepository.findAll();
        assertThat(itemPriceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsNotRequired() throws Exception {
        int databaseSizeBeforeTest = itemPriceRepository.findAll().size();
        // set the field null
        itemPrice.setStatus(null);

        // Create the ItemPrice, which fails.
        ItemPriceDTO itemPriceDTO = itemPriceMapper.toDto(itemPrice);


        restItemPriceMockMvc.perform(post("/api/item-prices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemPriceDTO)))
            .andExpect(status().isCreated());

        List<ItemPrice> itemPriceList = itemPriceRepository.findAll();
        assertThat(itemPriceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItemPrices() throws Exception {
        // Initialize the database
        itemPriceRepository.saveAndFlush(itemPrice);

        // Get all the itemPriceList
        restItemPriceMockMvc.perform(get("/api/item-prices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemPrice.getId().intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getItemPrice() throws Exception {
        // Initialize the database
        itemPriceRepository.saveAndFlush(itemPrice);

        // Get the itemPrice
        restItemPriceMockMvc.perform(get("/api/item-prices/{id}", itemPrice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(itemPrice.getId().intValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingItemPrice() throws Exception {
        // Get the itemPrice
        restItemPriceMockMvc.perform(get("/api/item-prices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemPrice() throws Exception {
        // Initialize the database
        itemPriceRepository.saveAndFlush(itemPrice);

        int databaseSizeBeforeUpdate = itemPriceRepository.findAll().size();

        // Update the itemPrice
        ItemPrice updatedItemPrice = itemPriceRepository.findById(itemPrice.getId()).get();
        // Disconnect from session so that the updates on updatedItemPrice are not directly saved in db
        em.detach(updatedItemPrice);
        updatedItemPrice
            .price(UPDATED_PRICE);
        ItemPriceDTO itemPriceDTO = itemPriceMapper.toDto(updatedItemPrice);

        restItemPriceMockMvc.perform(put("/api/item-prices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemPriceDTO)))
            .andExpect(status().isOk());

        // Validate the ItemPrice in the database
        List<ItemPrice> itemPriceList = itemPriceRepository.findAll();
        assertThat(itemPriceList).hasSize(databaseSizeBeforeUpdate);
        ItemPrice testItemPrice = itemPriceList.get(itemPriceList.size() - 1);
        assertThat(testItemPrice.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testItemPrice.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingItemPrice() throws Exception {
        int databaseSizeBeforeUpdate = itemPriceRepository.findAll().size();

        // Create the ItemPrice
        ItemPriceDTO itemPriceDTO = itemPriceMapper.toDto(itemPrice);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemPriceMockMvc.perform(put("/api/item-prices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemPriceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ItemPrice in the database
        List<ItemPrice> itemPriceList = itemPriceRepository.findAll();
        assertThat(itemPriceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItemPrice() throws Exception {
        // Initialize the database
        itemPriceRepository.saveAndFlush(itemPrice);

        int databaseSizeBeforeDelete = itemPriceRepository.findAll().size();

        // Delete the itemPrice
        restItemPriceMockMvc.perform(delete("/api/item-prices/{id}", itemPrice.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemPrice> itemPriceList = itemPriceRepository.findAll();
        assertThat(itemPriceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
