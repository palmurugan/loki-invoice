package com.way2invoice.bms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ItemPriceHistoryMapperTest {

    private ItemPriceHistoryMapper itemPriceHistoryMapper;

    @BeforeEach
    public void setUp() {
        itemPriceHistoryMapper = new ItemPriceHistoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(itemPriceHistoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(itemPriceHistoryMapper.fromId(null)).isNull();
    }
}
