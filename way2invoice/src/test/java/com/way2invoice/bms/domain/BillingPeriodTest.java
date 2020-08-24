package com.way2invoice.bms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class BillingPeriodTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BillingPeriod.class);
        BillingPeriod billingPeriod1 = new BillingPeriod();
        billingPeriod1.setId(1L);
        BillingPeriod billingPeriod2 = new BillingPeriod();
        billingPeriod2.setId(billingPeriod1.getId());
        assertThat(billingPeriod1).isEqualTo(billingPeriod2);
        billingPeriod2.setId(2L);
        assertThat(billingPeriod1).isNotEqualTo(billingPeriod2);
        billingPeriod1.setId(null);
        assertThat(billingPeriod1).isNotEqualTo(billingPeriod2);
    }
}
