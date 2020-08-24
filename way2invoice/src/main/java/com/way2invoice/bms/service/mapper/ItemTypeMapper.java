package com.way2invoice.bms.service.mapper;


import com.way2invoice.bms.domain.*;
import com.way2invoice.bms.service.dto.ItemTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemType} and its DTO {@link ItemTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ItemTypeMapper extends EntityMapper<ItemTypeDTO, ItemType> {



    default ItemType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ItemType itemType = new ItemType();
        itemType.setId(id);
        return itemType;
    }
}
