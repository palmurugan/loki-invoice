package com.way2invoice.bms.service.mapper;

import com.way2invoice.bms.domain.Country;
import com.way2invoice.bms.service.dto.CountryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper extends EntityMapper<CountryDTO, Country> {

    default Country fromId(Long id) {
        if (id == null) {
            return null;
        }
        Country country = new Country();
        country.setId(id);
        return country;
    }
}
