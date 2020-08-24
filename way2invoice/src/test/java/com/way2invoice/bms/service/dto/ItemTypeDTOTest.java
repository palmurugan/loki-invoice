package com.way2invoice.bms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.way2invoice.bms.web.rest.TestUtil;

public class ItemTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemTypeDTO.class);
        ItemTypeDTO itemTypeDTO1 = new ItemTypeDTO();
        itemTypeDTO1.setId(1L);
        ItemTypeDTO itemTypeDTO2 = new ItemTypeDTO();
        assertThat(itemTypeDTO1).isNotEqualTo(itemTypeDTO2);
        itemTypeDTO2.setId(itemTypeDTO1.getId());
        assertThat(itemTypeDTO1).isEqualTo(itemTypeDTO2);
        itemTypeDTO2.setId(2L);
        assertThat(itemTypeDTO1).isNotEqualTo(itemTypeDTO2);
        itemTypeDTO1.setId(null);
        assertThat(itemTypeDTO1).isNotEqualTo(itemTypeDTO2);
    }
}
