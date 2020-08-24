package com.way2invoice.bms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ItemTypeMapperTest {

    private ItemTypeMapper itemTypeMapper;

    @BeforeEach
    public void setUp() {
        itemTypeMapper = new ItemTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(itemTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(itemTypeMapper.fromId(null)).isNull();
    }
}
