package com.way2invoice.bms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomerContactMapperTest {

    private CustomerContactMapper customerContactMapper;

    @BeforeEach
    public void setUp() {
        customerContactMapper = new CustomerContactMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(customerContactMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(customerContactMapper.fromId(null)).isNull();
    }
}
