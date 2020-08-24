package com.way2invoice.bms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountGroupMapperTest {

    private AccountGroupMapper accountGroupMapper;

    @BeforeEach
    public void setUp() {
        accountGroupMapper = new AccountGroupMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(accountGroupMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(accountGroupMapper.fromId(null)).isNull();
    }
}
