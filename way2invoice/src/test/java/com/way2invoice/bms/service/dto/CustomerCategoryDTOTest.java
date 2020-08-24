package com.way2invoice.bms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class CustomerCategoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerCategoryDTO.class);
        CustomerCategoryDTO customerCategoryDTO1 = new CustomerCategoryDTO();
        customerCategoryDTO1.setId(1L);
        CustomerCategoryDTO customerCategoryDTO2 = new CustomerCategoryDTO();
        assertThat(customerCategoryDTO1).isNotEqualTo(customerCategoryDTO2);
        customerCategoryDTO2.setId(customerCategoryDTO1.getId());
        assertThat(customerCategoryDTO1).isEqualTo(customerCategoryDTO2);
        customerCategoryDTO2.setId(2L);
        assertThat(customerCategoryDTO1).isNotEqualTo(customerCategoryDTO2);
        customerCategoryDTO1.setId(null);
        assertThat(customerCategoryDTO1).isNotEqualTo(customerCategoryDTO2);
    }
}
