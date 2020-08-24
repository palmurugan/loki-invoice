package com.way2invoice.bms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomerCategoryMapperTest {

    private CustomerCategoryMapper customerCategoryMapper;

    @BeforeEach
    public void setUp() {
        customerCategoryMapper = new CustomerCategoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(customerCategoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(customerCategoryMapper.fromId(null)).isNull();
    }
}
