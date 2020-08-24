package com.way2invoice.bms.service.mapper;


import com.way2invoice.bms.domain.*;
import com.way2invoice.bms.service.dto.ItemPriceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemPrice} and its DTO {@link ItemPriceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ItemPriceMapper extends EntityMapper<ItemPriceDTO, ItemPrice> {



    default ItemPrice fromId(Long id) {
        if (id == null) {
            return null;
        }
        ItemPrice itemPrice = new ItemPrice();
        itemPrice.setId(id);
        return itemPrice;
    }
}
