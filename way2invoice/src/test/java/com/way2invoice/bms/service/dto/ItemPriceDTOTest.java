package com.way2invoice.bms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class ItemPriceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemPriceDTO.class);
        ItemPriceDTO itemPriceDTO1 = new ItemPriceDTO();
        itemPriceDTO1.setId(1L);
        ItemPriceDTO itemPriceDTO2 = new ItemPriceDTO();
        assertThat(itemPriceDTO1).isNotEqualTo(itemPriceDTO2);
        itemPriceDTO2.setId(itemPriceDTO1.getId());
        assertThat(itemPriceDTO1).isEqualTo(itemPriceDTO2);
        itemPriceDTO2.setId(2L);
        assertThat(itemPriceDTO1).isNotEqualTo(itemPriceDTO2);
        itemPriceDTO1.setId(null);
        assertThat(itemPriceDTO1).isNotEqualTo(itemPriceDTO2);
    }
}
