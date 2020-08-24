package com.way2invoice.bms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class BillingPeriodDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BillingPeriodDTO.class);
        BillingPeriodDTO billingPeriodDTO1 = new BillingPeriodDTO();
        billingPeriodDTO1.setId(1L);
        BillingPeriodDTO billingPeriodDTO2 = new BillingPeriodDTO();
        assertThat(billingPeriodDTO1).isNotEqualTo(billingPeriodDTO2);
        billingPeriodDTO2.setId(billingPeriodDTO1.getId());
        assertThat(billingPeriodDTO1).isEqualTo(billingPeriodDTO2);
        billingPeriodDTO2.setId(2L);
        assertThat(billingPeriodDTO1).isNotEqualTo(billingPeriodDTO2);
        billingPeriodDTO1.setId(null);
        assertThat(billingPeriodDTO1).isNotEqualTo(billingPeriodDTO2);
    }
}
