package com.way2invoice.bms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class AccountGroupDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountGroupDTO.class);
        AccountGroupDTO accountGroupDTO1 = new AccountGroupDTO();
        accountGroupDTO1.setId(1L);
        AccountGroupDTO accountGroupDTO2 = new AccountGroupDTO();
        assertThat(accountGroupDTO1).isNotEqualTo(accountGroupDTO2);
        accountGroupDTO2.setId(accountGroupDTO1.getId());
        assertThat(accountGroupDTO1).isEqualTo(accountGroupDTO2);
        accountGroupDTO2.setId(2L);
        assertThat(accountGroupDTO1).isNotEqualTo(accountGroupDTO2);
        accountGroupDTO1.setId(null);
        assertThat(accountGroupDTO1).isNotEqualTo(accountGroupDTO2);
    }
}
