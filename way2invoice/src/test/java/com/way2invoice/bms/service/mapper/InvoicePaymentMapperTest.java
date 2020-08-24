package com.way2invoice.bms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InvoicePaymentMapperTest {

    private InvoicePaymentMapper invoicePaymentMapper;

    @BeforeEach
    public void setUp() {
        invoicePaymentMapper = new InvoicePaymentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(invoicePaymentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(invoicePaymentMapper.fromId(null)).isNull();
    }
}
