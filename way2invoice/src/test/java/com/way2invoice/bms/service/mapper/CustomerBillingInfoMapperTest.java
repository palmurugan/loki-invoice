package com.way2invoice.bms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomerBillingInfoMapperTest {

    private CustomerBillingInfoMapper customerBillingInfoMapper;

    @BeforeEach
    public void setUp() {
        customerBillingInfoMapper = new CustomerBillingInfoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(customerBillingInfoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(customerBillingInfoMapper.fromId(null)).isNull();
    }
}
