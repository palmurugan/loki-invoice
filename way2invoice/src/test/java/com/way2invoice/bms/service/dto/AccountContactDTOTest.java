package com.way2invoice.bms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class AccountContactDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountContactDTO.class);
        AccountContactDTO accountContactDTO1 = new AccountContactDTO();
        accountContactDTO1.setId(1L);
        AccountContactDTO accountContactDTO2 = new AccountContactDTO();
        assertThat(accountContactDTO1).isNotEqualTo(accountContactDTO2);
        accountContactDTO2.setId(accountContactDTO1.getId());
        assertThat(accountContactDTO1).isEqualTo(accountContactDTO2);
        accountContactDTO2.setId(2L);
        assertThat(accountContactDTO1).isNotEqualTo(accountContactDTO2);
        accountContactDTO1.setId(null);
        assertThat(accountContactDTO1).isNotEqualTo(accountContactDTO2);
    }
}
