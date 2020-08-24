package com.way2invoice.bms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountTaxInfoMapperTest {

    private AccountTaxInfoMapper accountTaxInfoMapper;

    @BeforeEach
    public void setUp() {
        accountTaxInfoMapper = new AccountTaxInfoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(accountTaxInfoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(accountTaxInfoMapper.fromId(null)).isNull();
    }
}
