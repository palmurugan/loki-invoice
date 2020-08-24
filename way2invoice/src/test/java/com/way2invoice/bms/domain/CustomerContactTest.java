package com.way2invoice.bms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class CustomerContactTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerContact.class);
        CustomerContact customerContact1 = new CustomerContact();
        customerContact1.setId(1L);
        CustomerContact customerContact2 = new CustomerContact();
        customerContact2.setId(customerContact1.getId());
        assertThat(customerContact1).isEqualTo(customerContact2);
        customerContact2.setId(2L);
        assertThat(customerContact1).isNotEqualTo(customerContact2);
        customerContact1.setId(null);
        assertThat(customerContact1).isNotEqualTo(customerContact2);
    }
}
