package com.way2invoice.bms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BillingTypeMapperTest {

    private BillingTypeMapper billingTypeMapper;

    @BeforeEach
    public void setUp() {
        billingTypeMapper = new BillingTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(billingTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(billingTypeMapper.fromId(null)).isNull();
    }
}
