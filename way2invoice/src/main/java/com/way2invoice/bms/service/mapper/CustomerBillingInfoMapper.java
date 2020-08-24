package com.way2invoice.bms.service.mapper;


import com.way2invoice.bms.domain.*;
import com.way2invoice.bms.service.dto.CustomerBillingInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CustomerBillingInfo} and its DTO {@link CustomerBillingInfoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomerBillingInfoMapper extends EntityMapper<CustomerBillingInfoDTO, CustomerBillingInfo> {



    default CustomerBillingInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerBillingInfo customerBillingInfo = new CustomerBillingInfo();
        customerBillingInfo.setId(id);
        return customerBillingInfo;
    }
}
