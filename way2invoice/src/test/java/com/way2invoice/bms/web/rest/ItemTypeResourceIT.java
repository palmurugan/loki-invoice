package com.way2invoice.bms.web.rest;

import com.way2invoice.bms.Way2InvoiceApp;
import com.way2invoice.bms.domain.ItemType;
import com.way2invoice.bms.repository.ItemTypeRepository;
import com.way2invoice.bms.service.ItemTypeService;
import com.way2invoice.bms.service.dto.ItemTypeDTO;
import com.way2invoice.bms.service.mapper.ItemTypeMapper;

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
 * Integration tests for the {@link ItemTypeResource} REST controller.
 */
@SpringBootTest(classes = Way2InvoiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ItemTypeResourceIT {

    private static final Long DEFAULT_CLIENT_ID = 1L;
    private static final Long UPDATED_CLIENT_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.DEACTIVE;

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    @Autowired
    private ItemTypeMapper itemTypeMapper;

    @Autowired
    private ItemTypeService itemTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemTypeMockMvc;

    private ItemType itemType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemType createEntity(EntityManager em) {
        ItemType itemType = new ItemType()
            .clientId(DEFAULT_CLIENT_ID)
            .name(DEFAULT_NAME)
            .status(DEFAULT_STATUS);
        return itemType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemType createUpdatedEntity(EntityManager em) {
        ItemType itemType = new ItemType()
            .clientId(UPDATED_CLIENT_ID)
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS);
        return itemType;
    }

    @BeforeEach
    public void initTest() {
        itemType = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemType() throws Exception {
        int databaseSizeBeforeCreate = itemTypeRepository.findAll().size();
        // Create the ItemType
        ItemTypeDTO itemTypeDTO = itemTypeMapper.toDto(itemType);
        restItemTypeMockMvc.perform(post("/api/item-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ItemType in the database
        List<ItemType> itemTypeList = itemTypeRepository.findAll();
        assertThat(itemTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ItemType testItemType = itemTypeList.get(itemTypeList.size() - 1);
        assertThat(testItemType.getClientId()).isEqualTo(DEFAULT_CLIENT_ID);
        assertThat(testItemType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testItemType.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createItemTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemTypeRepository.findAll().size();

        // Create the ItemType with an existing ID
        itemType.setId(1L);
        ItemTypeDTO itemTypeDTO = itemTypeMapper.toDto(itemType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemTypeMockMvc.perform(post("/api/item-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ItemType in the database
        List<ItemType> itemTypeList = itemTypeRepository.findAll();
        assertThat(itemTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkClientIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemTypeRepository.findAll().size();
        // set the field null
        itemType.setClientId(null);

        // Create the ItemType, which fails.
        ItemTypeDTO itemTypeDTO = itemTypeMapper.toDto(itemType);


        restItemTypeMockMvc.perform(post("/api/item-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ItemType> itemTypeList = itemTypeRepository.findAll();
        assertThat(itemTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemTypeRepository.findAll().size();
        // set the field null
        itemType.setName(null);

        // Create the ItemType, which fails.
        ItemTypeDTO itemTypeDTO = itemTypeMapper.toDto(itemType);


        restItemTypeMockMvc.perform(post("/api/item-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ItemType> itemTypeList = itemTypeRepository.findAll();
        assertThat(itemTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemTypeRepository.findAll().size();
        // set the field null
        itemType.setStatus(null);

        // Create the ItemType, which fails.
        ItemTypeDTO itemTypeDTO = itemTypeMapper.toDto(itemType);


        restItemTypeMockMvc.perform(post("/api/item-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ItemType> itemTypeList = itemTypeRepository.findAll();
        assertThat(itemTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItemTypes() throws Exception {
        // Initialize the database
        itemTypeRepository.saveAndFlush(itemType);

        // Get all the itemTypeList
        restItemTypeMockMvc.perform(get("/api/item-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemType.getId().intValue())))
            .andExpect(jsonPath("$.[*].clientId").value(hasItem(DEFAULT_CLIENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getItemType() throws Exception {
        // Initialize the database
        itemTypeRepository.saveAndFlush(itemType);

        // Get the itemType
        restItemTypeMockMvc.perform(get("/api/item-types/{id}", itemType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(itemType.getId().intValue()))
            .andExpect(jsonPath("$.clientId").value(DEFAULT_CLIENT_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingItemType() throws Exception {
        // Get the itemType
        restItemTypeMockMvc.perform(get("/api/item-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemType() throws Exception {
        // Initialize the database
        itemTypeRepository.saveAndFlush(itemType);

        int databaseSizeBeforeUpdate = itemTypeRepository.findAll().size();

        // Update the itemType
        ItemType updatedItemType = itemTypeRepository.findById(itemType.getId()).get();
        // Disconnect from session so that the updates on updatedItemType are not directly saved in db
        em.detach(updatedItemType);
        updatedItemType
            .clientId(UPDATED_CLIENT_ID)
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS);
        ItemTypeDTO itemTypeDTO = itemTypeMapper.toDto(updatedItemType);

        restItemTypeMockMvc.perform(put("/api/item-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ItemType in the database
        List<ItemType> itemTypeList = itemTypeRepository.findAll();
        assertThat(itemTypeList).hasSize(databaseSizeBeforeUpdate);
        ItemType testItemType = itemTypeList.get(itemTypeList.size() - 1);
        assertThat(testItemType.getClientId()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testItemType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testItemType.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingItemType() throws Exception {
        int databaseSizeBeforeUpdate = itemTypeRepository.findAll().size();

        // Create the ItemType
        ItemTypeDTO itemTypeDTO = itemTypeMapper.toDto(itemType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemTypeMockMvc.perform(put("/api/item-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ItemType in the database
        List<ItemType> itemTypeList = itemTypeRepository.findAll();
        assertThat(itemTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItemType() throws Exception {
        // Initialize the database
        itemTypeRepository.saveAndFlush(itemType);

        int databaseSizeBeforeDelete = itemTypeRepository.findAll().size();

        // Delete the itemType
        restItemTypeMockMvc.perform(delete("/api/item-types/{id}", itemType.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemType> itemTypeList = itemTypeRepository.findAll();
        assertThat(itemTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
