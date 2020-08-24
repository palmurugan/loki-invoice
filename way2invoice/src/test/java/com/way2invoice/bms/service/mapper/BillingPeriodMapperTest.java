package com.way2invoice.bms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BillingPeriodMapperTest {

    private BillingPeriodMapper billingPeriodMapper;

    @BeforeEach
    public void setUp() {
        billingPeriodMapper = new BillingPeriodMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(billingPeriodMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(billingPeriodMapper.fromId(null)).isNull();
    }
}
