package com.way2invoice.bms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class AccountTaxInfoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountTaxInfoDTO.class);
        AccountTaxInfoDTO accountTaxInfoDTO1 = new AccountTaxInfoDTO();
        accountTaxInfoDTO1.setId(1L);
        AccountTaxInfoDTO accountTaxInfoDTO2 = new AccountTaxInfoDTO();
        assertThat(accountTaxInfoDTO1).isNotEqualTo(accountTaxInfoDTO2);
        accountTaxInfoDTO2.setId(accountTaxInfoDTO1.getId());
        assertThat(accountTaxInfoDTO1).isEqualTo(accountTaxInfoDTO2);
        accountTaxInfoDTO2.setId(2L);
        assertThat(accountTaxInfoDTO1).isNotEqualTo(accountTaxInfoDTO2);
        accountTaxInfoDTO1.setId(null);
        assertThat(accountTaxInfoDTO1).isNotEqualTo(accountTaxInfoDTO2);
    }
}
