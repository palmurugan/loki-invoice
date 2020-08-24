package com.way2invoice.bms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class InvoicePaymentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoicePaymentDTO.class);
        InvoicePaymentDTO invoicePaymentDTO1 = new InvoicePaymentDTO();
        invoicePaymentDTO1.setId(1L);
        InvoicePaymentDTO invoicePaymentDTO2 = new InvoicePaymentDTO();
        assertThat(invoicePaymentDTO1).isNotEqualTo(invoicePaymentDTO2);
        invoicePaymentDTO2.setId(invoicePaymentDTO1.getId());
        assertThat(invoicePaymentDTO1).isEqualTo(invoicePaymentDTO2);
        invoicePaymentDTO2.setId(2L);
        assertThat(invoicePaymentDTO1).isNotEqualTo(invoicePaymentDTO2);
        invoicePaymentDTO1.setId(null);
        assertThat(invoicePaymentDTO1).isNotEqualTo(invoicePaymentDTO2);
    }
}
