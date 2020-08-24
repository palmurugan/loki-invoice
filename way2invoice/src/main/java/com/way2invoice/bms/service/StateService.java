package com.way2invoice.bms.service;

import com.way2invoice.bms.common.service.GenericService;
import com.way2invoice.bms.domain.State;
import java.util.List;

public interface StateService extends GenericService<State, Long> {

    List<State> findByCountryId(Long countryId);

}
