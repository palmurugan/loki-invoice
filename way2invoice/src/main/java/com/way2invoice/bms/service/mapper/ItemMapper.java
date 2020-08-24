package com.way2invoice.bms.service.mapper;


import com.way2invoice.bms.domain.Item;
import com.way2invoice.bms.service.dto.ItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Item} and its DTO {@link ItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {ItemPriceMapper.class, ItemTypeMapper.class,
    UnitMapper.class, TaxMapper.class})
public interface ItemMapper extends EntityMapper<ItemDTO, Item> {

  @Mapping(source = "itemType.id", target = "itemTypeId")
  @Mapping(source = "unit.id", target = "unitId")
  @Mapping(source = "unit.name", target = "unit")
  @Mapping(source = "itemPrice.price", target = "itemPrice")
  @Mapping(source = "itemPrice.id", target = "itemPriceId")
  @Mapping(source = "tax.id", target = "taxId")
  @Mapping(source = "tax.name", target = "taxName")
  @Mapping(source = "tax.value", target = "taxValue")
  ItemDTO toDto(Item item);

  @Mapping(source = "itemTypeId", target = "itemType")
  @Mapping(source = "unitId", target = "unit")
  @Mapping(source = "taxId", target = "tax")
  @Mapping(source = "itemPriceId", target = "itemPrice.id")
  @Mapping(source = "itemPrice", target = "itemPrice.price")
  Item toEntity(ItemDTO itemDTO);

  default Item fromId(Long id) {
    if (id == null) {
      return null;
    }
    Item item = new Item();
    item.setId(id);
    return item;
  }
}
