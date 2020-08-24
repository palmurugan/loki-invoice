package com.way2invoice.bms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class ItemPriceHistoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemPriceHistoryDTO.class);
        ItemPriceHistoryDTO itemPriceHistoryDTO1 = new ItemPriceHistoryDTO();
        itemPriceHistoryDTO1.setId(1L);
        ItemPriceHistoryDTO itemPriceHistoryDTO2 = new ItemPriceHistoryDTO();
        assertThat(itemPriceHistoryDTO1).isNotEqualTo(itemPriceHistoryDTO2);
        itemPriceHistoryDTO2.setId(itemPriceHistoryDTO1.getId());
        assertThat(itemPriceHistoryDTO1).isEqualTo(itemPriceHistoryDTO2);
        itemPriceHistoryDTO2.setId(2L);
        assertThat(itemPriceHistoryDTO1).isNotEqualTo(itemPriceHistoryDTO2);
        itemPriceHistoryDTO1.setId(null);
        assertThat(itemPriceHistoryDTO1).isNotEqualTo(itemPriceHistoryDTO2);
    }
}
