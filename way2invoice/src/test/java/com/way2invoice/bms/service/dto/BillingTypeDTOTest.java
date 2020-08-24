package com.way2invoice.bms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class BillingTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BillingTypeDTO.class);
        BillingTypeDTO billingTypeDTO1 = new BillingTypeDTO();
        billingTypeDTO1.setId(1L);
        BillingTypeDTO billingTypeDTO2 = new BillingTypeDTO();
        assertThat(billingTypeDTO1).isNotEqualTo(billingTypeDTO2);
        billingTypeDTO2.setId(billingTypeDTO1.getId());
        assertThat(billingTypeDTO1).isEqualTo(billingTypeDTO2);
        billingTypeDTO2.setId(2L);
        assertThat(billingTypeDTO1).isNotEqualTo(billingTypeDTO2);
        billingTypeDTO1.setId(null);
        assertThat(billingTypeDTO1).isNotEqualTo(billingTypeDTO2);
    }
}
