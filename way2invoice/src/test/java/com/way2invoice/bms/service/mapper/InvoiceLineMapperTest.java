package com.way2invoice.bms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InvoiceLineMapperTest {

    private InvoiceLineMapper invoiceLineMapper;

    @BeforeEach
    public void setUp() {
        invoiceLineMapper = new InvoiceLineMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(invoiceLineMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(invoiceLineMapper.fromId(null)).isNull();
    }
}
