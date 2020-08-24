package com.way2invoice.bms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class CustomerContactDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerContactDTO.class);
        CustomerContactDTO customerContactDTO1 = new CustomerContactDTO();
        customerContactDTO1.setId(1L);
        CustomerContactDTO customerContactDTO2 = new CustomerContactDTO();
        assertThat(customerContactDTO1).isNotEqualTo(customerContactDTO2);
        customerContactDTO2.setId(customerContactDTO1.getId());
        assertThat(customerContactDTO1).isEqualTo(customerContactDTO2);
        customerContactDTO2.setId(2L);
        assertThat(customerContactDTO1).isNotEqualTo(customerContactDTO2);
        customerContactDTO1.setId(null);
        assertThat(customerContactDTO1).isNotEqualTo(customerContactDTO2);
    }
}
