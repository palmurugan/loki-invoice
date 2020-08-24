package com.way2invoice.bms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class BillingTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BillingType.class);
        BillingType billingType1 = new BillingType();
        billingType1.setId(1L);
        BillingType billingType2 = new BillingType();
        billingType2.setId(billingType1.getId());
        assertThat(billingType1).isEqualTo(billingType2);
        billingType2.setId(2L);
        assertThat(billingType1).isNotEqualTo(billingType2);
        billingType1.setId(null);
        assertThat(billingType1).isNotEqualTo(billingType2);
    }
}
