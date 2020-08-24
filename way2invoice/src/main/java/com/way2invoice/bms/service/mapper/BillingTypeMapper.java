package com.way2invoice.bms.service.mapper;


import com.way2invoice.bms.domain.*;
import com.way2invoice.bms.service.dto.BillingTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BillingType} and its DTO {@link BillingTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BillingTypeMapper extends EntityMapper<BillingTypeDTO, BillingType> {



    default BillingType fromId(Long id) {
        if (id == null) {
            return null;
        }
        BillingType billingType = new BillingType();
        billingType.setId(id);
        return billingType;
    }
}
