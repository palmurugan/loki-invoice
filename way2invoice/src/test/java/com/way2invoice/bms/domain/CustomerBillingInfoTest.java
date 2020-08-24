package com.way2invoice.bms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class CustomerBillingInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerBillingInfo.class);
        CustomerBillingInfo customerBillingInfo1 = new CustomerBillingInfo();
        customerBillingInfo1.setId(1L);
        CustomerBillingInfo customerBillingInfo2 = new CustomerBillingInfo();
        customerBillingInfo2.setId(customerBillingInfo1.getId());
        assertThat(customerBillingInfo1).isEqualTo(customerBillingInfo2);
        customerBillingInfo2.setId(2L);
        assertThat(customerBillingInfo1).isNotEqualTo(customerBillingInfo2);
        customerBillingInfo1.setId(null);
        assertThat(customerBillingInfo1).isNotEqualTo(customerBillingInfo2);
    }
}
