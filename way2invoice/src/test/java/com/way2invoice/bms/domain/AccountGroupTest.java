package com.way2invoice.bms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class AccountGroupTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountGroup.class);
        AccountGroup accountGroup1 = new AccountGroup();
        accountGroup1.setId(1L);
        AccountGroup accountGroup2 = new AccountGroup();
        accountGroup2.setId(accountGroup1.getId());
        assertThat(accountGroup1).isEqualTo(accountGroup2);
        accountGroup2.setId(2L);
        assertThat(accountGroup1).isNotEqualTo(accountGroup2);
        accountGroup1.setId(null);
        assertThat(accountGroup1).isNotEqualTo(accountGroup2);
    }
}
