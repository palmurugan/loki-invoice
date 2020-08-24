package com.way2invoice.bms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class CustomerCategoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerCategory.class);
        CustomerCategory customerCategory1 = new CustomerCategory();
        customerCategory1.setId(1L);
        CustomerCategory customerCategory2 = new CustomerCategory();
        customerCategory2.setId(customerCategory1.getId());
        assertThat(customerCategory1).isEqualTo(customerCategory2);
        customerCategory2.setId(2L);
        assertThat(customerCategory1).isNotEqualTo(customerCategory2);
        customerCategory1.setId(null);
        assertThat(customerCategory1).isNotEqualTo(customerCategory2);
    }
}
