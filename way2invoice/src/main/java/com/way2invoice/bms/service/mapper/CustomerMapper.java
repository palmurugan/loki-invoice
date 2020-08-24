package com.way2invoice.bms.service.mapper;


import com.way2invoice.bms.domain.Customer;
import com.way2invoice.bms.service.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Customer} and its DTO {@link CustomerDTO}.
 */
@Mapper(componentModel = "spring", uses = {CustomerCategoryMapper.class,
    CustomerBillingInfoMapper.class, CurrencyMapper.class, BillingTypeMapper.class,
    BillingPeriodMapper.class})
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {

  @Mapping(source = "customerCategory.id", target = "customerCategoryId")
  @Mapping(source = "customerCategory.name", target = "categoryName")
  @Mapping(source = "currency.id", target = "currencyId")
  @Mapping(source = "currency.name", target = "currency")
  @Mapping(source = "billingType.id", target = "billingTypeId")
  @Mapping(source = "billingType.name", target = "billingType")
  @Mapping(source = "billingPeriod.id", target = "billingPeriodId")
  @Mapping(source = "billingPeriod.name", target = "billingPeriod")
  CustomerDTO toDto(Customer customer);

  @Mapping(source = "customerCategoryId", target = "customerCategory")
  @Mapping(source = "currencyId", target = "currency")
  @Mapping(source = "billingTypeId", target = "billingType")
  @Mapping(source = "billingPeriodId", target = "billingPeriod")
  @Mapping(target = "removeCustomerContact", ignore = true)
  Customer toEntity(CustomerDTO customerDTO);

  default Customer fromId(Long id) {
    if (id == null) {
      return null;
    }
    Customer customer = new Customer();
    customer.setId(id);
    return customer;
  }
}
