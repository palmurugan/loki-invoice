package com.way2invoice.bms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class PaymentMethodDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentMethodDTO.class);
        PaymentMethodDTO paymentMethodDTO1 = new PaymentMethodDTO();
        paymentMethodDTO1.setId(1L);
        PaymentMethodDTO paymentMethodDTO2 = new PaymentMethodDTO();
        assertThat(paymentMethodDTO1).isNotEqualTo(paymentMethodDTO2);
        paymentMethodDTO2.setId(paymentMethodDTO1.getId());
        assertThat(paymentMethodDTO1).isEqualTo(paymentMethodDTO2);
        paymentMethodDTO2.setId(2L);
        assertThat(paymentMethodDTO1).isNotEqualTo(paymentMethodDTO2);
        paymentMethodDTO1.setId(null);
        assertThat(paymentMethodDTO1).isNotEqualTo(paymentMethodDTO2);
    }
}
