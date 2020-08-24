package com.way2invoice.bms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class AccountTaxInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountTaxInfo.class);
        AccountTaxInfo accountTaxInfo1 = new AccountTaxInfo();
        accountTaxInfo1.setId(1L);
        AccountTaxInfo accountTaxInfo2 = new AccountTaxInfo();
        accountTaxInfo2.setId(accountTaxInfo1.getId());
        assertThat(accountTaxInfo1).isEqualTo(accountTaxInfo2);
        accountTaxInfo2.setId(2L);
        assertThat(accountTaxInfo1).isNotEqualTo(accountTaxInfo2);
        accountTaxInfo1.setId(null);
        assertThat(accountTaxInfo1).isNotEqualTo(accountTaxInfo2);
    }
}
