package com.way2invoice.bms.service.mapper;


import com.way2invoice.bms.domain.*;
import com.way2invoice.bms.service.dto.ItemPriceHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemPriceHistory} and its DTO {@link ItemPriceHistoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ItemPriceHistoryMapper extends EntityMapper<ItemPriceHistoryDTO, ItemPriceHistory> {



    default ItemPriceHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        ItemPriceHistory itemPriceHistory = new ItemPriceHistory();
        itemPriceHistory.setId(id);
        return itemPriceHistory;
    }
}
