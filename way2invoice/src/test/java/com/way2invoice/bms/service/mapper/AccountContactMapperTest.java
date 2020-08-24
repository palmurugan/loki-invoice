package com.way2invoice.bms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountContactMapperTest {

    private AccountContactMapper accountContactMapper;

    @BeforeEach
    public void setUp() {
        accountContactMapper = new AccountContactMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(accountContactMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(accountContactMapper.fromId(null)).isNull();
    }
}
