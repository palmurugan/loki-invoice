package com.way2invoice.bms.service.mapper;


import com.way2invoice.bms.domain.*;
import com.way2invoice.bms.service.dto.BillingPeriodDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BillingPeriod} and its DTO {@link BillingPeriodDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BillingPeriodMapper extends EntityMapper<BillingPeriodDTO, BillingPeriod> {



    default BillingPeriod fromId(Long id) {
        if (id == null) {
            return null;
        }
        BillingPeriod billingPeriod = new BillingPeriod();
        billingPeriod.setId(id);
        return billingPeriod;
    }
}
