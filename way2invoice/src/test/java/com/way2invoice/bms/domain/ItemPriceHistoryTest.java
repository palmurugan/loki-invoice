package com.way2invoice.bms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class ItemPriceHistoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemPriceHistory.class);
        ItemPriceHistory itemPriceHistory1 = new ItemPriceHistory();
        itemPriceHistory1.setId(1L);
        ItemPriceHistory itemPriceHistory2 = new ItemPriceHistory();
        itemPriceHistory2.setId(itemPriceHistory1.getId());
        assertThat(itemPriceHistory1).isEqualTo(itemPriceHistory2);
        itemPriceHistory2.setId(2L);
        assertThat(itemPriceHistory1).isNotEqualTo(itemPriceHistory2);
        itemPriceHistory1.setId(null);
        assertThat(itemPriceHistory1).isNotEqualTo(itemPriceHistory2);
    }
}
