package com.way2invoice.bms.service.mapper;


import com.way2invoice.bms.domain.*;
import com.way2invoice.bms.service.dto.CustomerCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CustomerCategory} and its DTO {@link CustomerCategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomerCategoryMapper extends EntityMapper<CustomerCategoryDTO, CustomerCategory> {



    default CustomerCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerCategory customerCategory = new CustomerCategory();
        customerCategory.setId(id);
        return customerCategory;
    }
}
