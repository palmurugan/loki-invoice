package com.way2invoice.bms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class ItemPriceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemPrice.class);
        ItemPrice itemPrice1 = new ItemPrice();
        itemPrice1.setId(1L);
        ItemPrice itemPrice2 = new ItemPrice();
        itemPrice2.setId(itemPrice1.getId());
        assertThat(itemPrice1).isEqualTo(itemPrice2);
        itemPrice2.setId(2L);
        assertThat(itemPrice1).isNotEqualTo(itemPrice2);
        itemPrice1.setId(null);
        assertThat(itemPrice1).isNotEqualTo(itemPrice2);
    }
}
