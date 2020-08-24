package com.way2invoice.bms.service;

import com.way2invoice.bms.common.service.GenericService;
import com.way2invoice.bms.domain.Country;
import java.util.List;

public interface CountryService extends GenericService<Country, Long> {

    List<Country> findAll();
}
