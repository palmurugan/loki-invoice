package com.way2invoice.bms.service.mapper;

import com.way2invoice.bms.domain.OrderLine;
import com.way2invoice.bms.service.dto.OrderLineDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author palmuruganc
 * <p>
 * Mapper for the entity {@link OrderLine} and its DTO {@link OrderLineDTO}
 */
@Mapper(componentModel = "spring", uses = {InvoiceMapper.class, ItemMapper.class})
public interface OrderLineMapper extends EntityMapper<OrderLineDTO, OrderLine> {

  @Mapping(source = "item.id", target = "itemId")
  OrderLineDTO toDto(OrderLine orderLine);

  @Mapping(source = "itemId", target = "item")
  OrderLine toEntity(OrderLineDTO orderLineDTO);

  default OrderLine fromId(Long id) {
    if (id == null) {
      return null;
    }
    OrderLine orderLine = new OrderLine();
    orderLine.setId(id);
    return orderLine;
  }
}
