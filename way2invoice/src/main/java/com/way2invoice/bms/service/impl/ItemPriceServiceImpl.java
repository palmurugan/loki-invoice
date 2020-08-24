package com.way2invoice.bms.service.impl;

import com.way2invoice.bms.service.ItemPriceService;
import com.way2invoice.bms.domain.ItemPrice;
import com.way2invoice.bms.repository.ItemPriceRepository;
import com.way2invoice.bms.service.dto.ItemPriceDTO;
import com.way2invoice.bms.service.mapper.ItemPriceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ItemPrice}.
 */
@Service
@Transactional
public class ItemPriceServiceImpl implements ItemPriceService {

    private final Logger log = LoggerFactory.getLogger(ItemPriceServiceImpl.class);

    private final ItemPriceRepository itemPriceRepository;

    private final ItemPriceMapper itemPriceMapper;

    public ItemPriceServiceImpl(ItemPriceRepository itemPriceRepository, ItemPriceMapper itemPriceMapper) {
        this.itemPriceRepository = itemPriceRepository;
        this.itemPriceMapper = itemPriceMapper;
    }

    /**
     * Save a itemPrice.
     *
     * @param itemPriceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ItemPriceDTO save(ItemPriceDTO itemPriceDTO) {
        log.debug("Request to save ItemPrice : {}", itemPriceDTO);
        ItemPrice itemPrice = itemPriceMapper.toEntity(itemPriceDTO);
        itemPrice = itemPriceRepository.save(itemPrice);
        return itemPriceMapper.toDto(itemPrice);
    }

    /**
     * Get all the itemPrices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ItemPriceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemPrices");
        return itemPriceRepository.findAll(pageable)
            .map(itemPriceMapper::toDto);
    }


    /**
     * Get one itemPrice by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ItemPriceDTO> findOne(Long id) {
        log.debug("Request to get ItemPrice : {}", id);
        return itemPriceRepository.findById(id)
            .map(itemPriceMapper::toDto);
    }

    /**
     * Delete the itemPrice by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemPrice : {}", id);
        itemPriceRepository.deleteById(id);
    }
}
