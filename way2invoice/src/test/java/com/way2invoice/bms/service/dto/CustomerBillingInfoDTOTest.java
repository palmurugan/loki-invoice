package com.way2invoice.bms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class CustomerBillingInfoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerBillingInfoDTO.class);
        CustomerBillingInfoDTO customerBillingInfoDTO1 = new CustomerBillingInfoDTO();
        customerBillingInfoDTO1.setId(1L);
        CustomerBillingInfoDTO customerBillingInfoDTO2 = new CustomerBillingInfoDTO();
        assertThat(customerBillingInfoDTO1).isNotEqualTo(customerBillingInfoDTO2);
        customerBillingInfoDTO2.setId(customerBillingInfoDTO1.getId());
        assertThat(customerBillingInfoDTO1).isEqualTo(customerBillingInfoDTO2);
        customerBillingInfoDTO2.setId(2L);
        assertThat(customerBillingInfoDTO1).isNotEqualTo(customerBillingInfoDTO2);
        customerBillingInfoDTO1.setId(null);
        assertThat(customerBillingInfoDTO1).isNotEqualTo(customerBillingInfoDTO2);
    }
}
