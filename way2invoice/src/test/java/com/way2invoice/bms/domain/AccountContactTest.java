package com.way2invoice.bms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class AccountContactTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountContact.class);
        AccountContact accountContact1 = new AccountContact();
        accountContact1.setId(1L);
        AccountContact accountContact2 = new AccountContact();
        accountContact2.setId(accountContact1.getId());
        assertThat(accountContact1).isEqualTo(accountContact2);
        accountContact2.setId(2L);
        assertThat(accountContact1).isNotEqualTo(accountContact2);
        accountContact1.setId(null);
        assertThat(accountContact1).isNotEqualTo(accountContact2);
    }
}
