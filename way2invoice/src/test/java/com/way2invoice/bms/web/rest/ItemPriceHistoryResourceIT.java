package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.Way2InvoiceApp;
import com.way2invoice.bms.domain.ItemPriceHistory;
import com.way2invoice.bms.repository.ItemPriceHistoryRepository;
import com.way2invoice.bms.service.ItemPriceHistoryService;
import com.way2invoice.bms.service.dto.ItemPriceHistoryDTO;
import com.way2invoice.bms.service.mapper.ItemPriceHistoryMapper;

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

/**
 * Integration tests for the {@link ItemPriceHistoryResource} REST controller.
 */
@SpringBootTest(classes = Way2InvoiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ItemPriceHistoryResourceIT {

    private static final Long DEFAULT_ITEM_ID = 1L;
    private static final Long UPDATED_ITEM_ID = 2L;

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(2);

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ItemPriceHistoryRepository itemPriceHistoryRepository;

    @Autowired
    private ItemPriceHistoryMapper itemPriceHistoryMapper;

    @Autowired
    private ItemPriceHistoryService itemPriceHistoryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemPriceHistoryMockMvc;

    private ItemPriceHistory itemPriceHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemPriceHistory createEntity(EntityManager em) {
        ItemPriceHistory itemPriceHistory = new ItemPriceHistory()
            .itemId(DEFAULT_ITEM_ID)
            .price(DEFAULT_PRICE)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE);
        return itemPriceHistory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemPriceHistory createUpdatedEntity(EntityManager em) {
        ItemPriceHistory itemPriceHistory = new ItemPriceHistory()
            .itemId(UPDATED_ITEM_ID)
            .price(UPDATED_PRICE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE);
        return itemPriceHistory;
    }

    @BeforeEach
    public void initTest() {
        itemPriceHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemPriceHistory() throws Exception {
        int databaseSizeBeforeCreate = itemPriceHistoryRepository.findAll().size();
        // Create the ItemPriceHistory
        ItemPriceHistoryDTO itemPriceHistoryDTO = itemPriceHistoryMapper.toDto(itemPriceHistory);
        restItemPriceHistoryMockMvc.perform(post("/api/item-price-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemPriceHistoryDTO)))
            .andExpect(status().isCreated());

        // Validate the ItemPriceHistory in the database
        List<ItemPriceHistory> itemPriceHistoryList = itemPriceHistoryRepository.findAll();
        assertThat(itemPriceHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        ItemPriceHistory testItemPriceHistory = itemPriceHistoryList.get(itemPriceHistoryList.size() - 1);
        assertThat(testItemPriceHistory.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testItemPriceHistory.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testItemPriceHistory.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testItemPriceHistory.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    }

    @Test
    @Transactional
    public void createItemPriceHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemPriceHistoryRepository.findAll().size();

        // Create the ItemPriceHistory with an existing ID
        itemPriceHistory.setId(1L);
        ItemPriceHistoryDTO itemPriceHistoryDTO = itemPriceHistoryMapper.toDto(itemPriceHistory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemPriceHistoryMockMvc.perform(post("/api/item-price-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemPriceHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ItemPriceHistory in the database
        List<ItemPriceHistory> itemPriceHistoryList = itemPriceHistoryRepository.findAll();
        assertThat(itemPriceHistoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkItemIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemPriceHistoryRepository.findAll().size();
        // set the field null
        itemPriceHistory.setItemId(null);

        // Create the ItemPriceHistory, which fails.
        ItemPriceHistoryDTO itemPriceHistoryDTO = itemPriceHistoryMapper.toDto(itemPriceHistory);


        restItemPriceHistoryMockMvc.perform(post("/api/item-price-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemPriceHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<ItemPriceHistory> itemPriceHistoryList = itemPriceHistoryRepository.findAll();
        assertThat(itemPriceHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemPriceHistoryRepository.findAll().size();
        // set the field null
        itemPriceHistory.setPrice(null);

        // Create the ItemPriceHistory, which fails.
        ItemPriceHistoryDTO itemPriceHistoryDTO = itemPriceHistoryMapper.toDto(itemPriceHistory);


        restItemPriceHistoryMockMvc.perform(post("/api/item-price-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemPriceHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<ItemPriceHistory> itemPriceHistoryList = itemPriceHistoryRepository.findAll();
        assertThat(itemPriceHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemPriceHistoryRepository.findAll().size();
        // set the field null
        itemPriceHistory.setStartDate(null);

        // Create the ItemPriceHistory, which fails.
        ItemPriceHistoryDTO itemPriceHistoryDTO = itemPriceHistoryMapper.toDto(itemPriceHistory);


        restItemPriceHistoryMockMvc.perform(post("/api/item-price-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemPriceHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<ItemPriceHistory> itemPriceHistoryList = itemPriceHistoryRepository.findAll();
        assertThat(itemPriceHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItemPriceHistories() throws Exception {
        // Initialize the database
        itemPriceHistoryRepository.saveAndFlush(itemPriceHistory);

        // Get all the itemPriceHistoryList
        restItemPriceHistoryMockMvc.perform(get("/api/item-price-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemPriceHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getItemPriceHistory() throws Exception {
        // Initialize the database
        itemPriceHistoryRepository.saveAndFlush(itemPriceHistory);

        // Get the itemPriceHistory
        restItemPriceHistoryMockMvc.perform(get("/api/item-price-histories/{id}", itemPriceHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(itemPriceHistory.getId().intValue()))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingItemPriceHistory() throws Exception {
        // Get the itemPriceHistory
        restItemPriceHistoryMockMvc.perform(get("/api/item-price-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemPriceHistory() throws Exception {
        // Initialize the database
        itemPriceHistoryRepository.saveAndFlush(itemPriceHistory);

        int databaseSizeBeforeUpdate = itemPriceHistoryRepository.findAll().size();

        // Update the itemPriceHistory
        ItemPriceHistory updatedItemPriceHistory = itemPriceHistoryRepository.findById(itemPriceHistory.getId()).get();
        // Disconnect from session so that the updates on updatedItemPriceHistory are not directly saved in db
        em.detach(updatedItemPriceHistory);
        updatedItemPriceHistory
            .itemId(UPDATED_ITEM_ID)
            .price(UPDATED_PRICE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE);
        ItemPriceHistoryDTO itemPriceHistoryDTO = itemPriceHistoryMapper.toDto(updatedItemPriceHistory);

        restItemPriceHistoryMockMvc.perform(put("/api/item-price-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemPriceHistoryDTO)))
            .andExpect(status().isOk());

        // Validate the ItemPriceHistory in the database
        List<ItemPriceHistory> itemPriceHistoryList = itemPriceHistoryRepository.findAll();
        assertThat(itemPriceHistoryList).hasSize(databaseSizeBeforeUpdate);
        ItemPriceHistory testItemPriceHistory = itemPriceHistoryList.get(itemPriceHistoryList.size() - 1);
        assertThat(testItemPriceHistory.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testItemPriceHistory.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testItemPriceHistory.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testItemPriceHistory.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingItemPriceHistory() throws Exception {
        int databaseSizeBeforeUpdate = itemPriceHistoryRepository.findAll().size();

        // Create the ItemPriceHistory
        ItemPriceHistoryDTO itemPriceHistoryDTO = itemPriceHistoryMapper.toDto(itemPriceHistory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemPriceHistoryMockMvc.perform(put("/api/item-price-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemPriceHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ItemPriceHistory in the database
        List<ItemPriceHistory> itemPriceHistoryList = itemPriceHistoryRepository.findAll();
        assertThat(itemPriceHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItemPriceHistory() throws Exception {
        // Initialize the database
        itemPriceHistoryRepository.saveAndFlush(itemPriceHistory);

        int databaseSizeBeforeDelete = itemPriceHistoryRepository.findAll().size();

        // Delete the itemPriceHistory
        restItemPriceHistoryMockMvc.perform(delete("/api/item-price-histories/{id}", itemPriceHistory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemPriceHistory> itemPriceHistoryList = itemPriceHistoryRepository.findAll();
        assertThat(itemPriceHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
