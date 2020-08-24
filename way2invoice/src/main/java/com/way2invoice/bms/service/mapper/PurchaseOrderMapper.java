package com.way2invoice.bms.service.mapper;

import com.way2invoice.bms.domain.PurchaseOrder;
import com.way2invoice.bms.service.dto.PurchaseOrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author palmuruganc
 * <p>
 * Mapper for the entity {@link PurchaseOrder} and its dto {@link PurchaseOrderDTO}
 */
@Mapper(componentModel = "spring", uses = {OrderLineMapper.class, CustomerMapper.class,
    CurrencyMapper.class, BillingTypeMapper.class})
public interface PurchaseOrderMapper extends EntityMapper<PurchaseOrderDTO, PurchaseOrder> {

  @Mapping(source = "customer.id", target = "customerId")
  @Mapping(source = "billingType.id", target = "billingTypeId")
  @Mapping(source = "currency.id", target = "currencyId")
  PurchaseOrderDTO toDto(PurchaseOrder purchaseOrder);

  @Mapping(source = "customerId", target = "customer")
  @Mapping(source = "billingTypeId", target = "billingType")
  @Mapping(source = "currencyId", target = "currency")
  PurchaseOrder toEntity(PurchaseOrderDTO purchaseOrderDTO);

  default PurchaseOrder fromId(Long id) {
    if (id == null) {
      return null;
    }
    PurchaseOrder purchaseOrder = new PurchaseOrder();
    purchaseOrder.setId(id);
    return purchaseOrder;
  }
}
