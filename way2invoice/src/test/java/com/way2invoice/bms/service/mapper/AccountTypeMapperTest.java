package com.way2invoice.bms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountTypeMapperTest {

    private AccountTypeMapper accountTypeMapper;

    @BeforeEach
    public void setUp() {
        accountTypeMapper = new AccountTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(accountTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(accountTypeMapper.fromId(null)).isNull();
    }
}
