package com.way2invoice.bms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ItemPriceMapperTest {

    private ItemPriceMapper itemPriceMapper;

    @BeforeEach
    public void setUp() {
        itemPriceMapper = new ItemPriceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(itemPriceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(itemPriceMapper.fromId(null)).isNull();
    }
}
