package com.way2invoice.bms.service.impl;

import com.way2invoice.bms.service.ItemPriceHistoryService;
import com.way2invoice.bms.domain.ItemPriceHistory;
import com.way2invoice.bms.repository.ItemPriceHistoryRepository;
import com.way2invoice.bms.service.dto.ItemPriceHistoryDTO;
import com.way2invoice.bms.service.mapper.ItemPriceHistoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ItemPriceHistory}.
 */
@Service
@Transactional
public class ItemPriceHistoryServiceImpl implements ItemPriceHistoryService {

    private final Logger log = LoggerFactory.getLogger(ItemPriceHistoryServiceImpl.class);

    private final ItemPriceHistoryRepository itemPriceHistoryRepository;

    private final ItemPriceHistoryMapper itemPriceHistoryMapper;

    public ItemPriceHistoryServiceImpl(ItemPriceHistoryRepository itemPriceHistoryRepository, ItemPriceHistoryMapper itemPriceHistoryMapper) {
        this.itemPriceHistoryRepository = itemPriceHistoryRepository;
        this.itemPriceHistoryMapper = itemPriceHistoryMapper;
    }

    /**
     * Save a itemPriceHistory.
     *
     * @param itemPriceHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ItemPriceHistoryDTO save(ItemPriceHistoryDTO itemPriceHistoryDTO) {
        log.debug("Request to save ItemPriceHistory : {}", itemPriceHistoryDTO);
        ItemPriceHistory itemPriceHistory = itemPriceHistoryMapper.toEntity(itemPriceHistoryDTO);
        itemPriceHistory = itemPriceHistoryRepository.save(itemPriceHistory);
        return itemPriceHistoryMapper.toDto(itemPriceHistory);
    }

    /**
     * Get all the itemPriceHistories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ItemPriceHistoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemPriceHistories");
        return itemPriceHistoryRepository.findAll(pageable)
            .map(itemPriceHistoryMapper::toDto);
    }


    /**
     * Get one itemPriceHistory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ItemPriceHistoryDTO> findOne(Long id) {
        log.debug("Request to get ItemPriceHistory : {}", id);
        return itemPriceHistoryRepository.findById(id)
            .map(itemPriceHistoryMapper::toDto);
    }

    /**
     * Delete the itemPriceHistory by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemPriceHistory : {}", id);
        itemPriceHistoryRepository.deleteById(id);
    }
}
