package com.way2invoice.bms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class InvoicePaymentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoicePayment.class);
        InvoicePayment invoicePayment1 = new InvoicePayment();
        invoicePayment1.setId(1L);
        InvoicePayment invoicePayment2 = new InvoicePayment();
        invoicePayment2.setId(invoicePayment1.getId());
        assertThat(invoicePayment1).isEqualTo(invoicePayment2);
        invoicePayment2.setId(2L);
        assertThat(invoicePayment1).isNotEqualTo(invoicePayment2);
        invoicePayment1.setId(null);
        assertThat(invoicePayment1).isNotEqualTo(invoicePayment2);
    }
}
