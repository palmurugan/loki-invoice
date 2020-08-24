package com.way2invoice.bms.service.mapper;


import com.way2invoice.bms.domain.*;
import com.way2invoice.bms.service.dto.CustomerContactDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CustomerContact} and its DTO {@link CustomerContactDTO}.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface CustomerContactMapper extends EntityMapper<CustomerContactDTO, CustomerContact> {

    @Mapping(source = "customer.id", target = "customerId")
    CustomerContactDTO toDto(CustomerContact customerContact);

    @Mapping(source = "customerId", target = "customer")
    CustomerContact toEntity(CustomerContactDTO customerContactDTO);

    default CustomerContact fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerContact customerContact = new CustomerContact();
        customerContact.setId(id);
        return customerContact;
    }
}
