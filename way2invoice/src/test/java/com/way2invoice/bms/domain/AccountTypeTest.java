package com.way2invoice.bms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class AccountTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountType.class);
        AccountType accountType1 = new AccountType();
        accountType1.setId(1L);
        AccountType accountType2 = new AccountType();
        accountType2.setId(accountType1.getId());
        assertThat(accountType1).isEqualTo(accountType2);
        accountType2.setId(2L);
        assertThat(accountType1).isNotEqualTo(accountType2);
        accountType1.setId(null);
        assertThat(accountType1).isNotEqualTo(accountType2);
    }
}
