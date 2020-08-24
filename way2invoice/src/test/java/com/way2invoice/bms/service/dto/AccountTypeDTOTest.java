package com.way2invoice.bms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class AccountTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountTypeDTO.class);
        AccountTypeDTO accountTypeDTO1 = new AccountTypeDTO();
        accountTypeDTO1.setId(1L);
        AccountTypeDTO accountTypeDTO2 = new AccountTypeDTO();
        assertThat(accountTypeDTO1).isNotEqualTo(accountTypeDTO2);
        accountTypeDTO2.setId(accountTypeDTO1.getId());
        assertThat(accountTypeDTO1).isEqualTo(accountTypeDTO2);
        accountTypeDTO2.setId(2L);
        assertThat(accountTypeDTO1).isNotEqualTo(accountTypeDTO2);
        accountTypeDTO1.setId(null);
        assertThat(accountTypeDTO1).isNotEqualTo(accountTypeDTO2);
    }
}
