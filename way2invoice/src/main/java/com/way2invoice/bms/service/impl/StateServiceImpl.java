package com.way2invoice.bms.service.impl;

import com.way2invoice.bms.common.service.impl.GenericServiceImpl;
import com.way2invoice.bms.domain.State;
import com.way2invoice.bms.repository.StateRepository;
import com.way2invoice.bms.service.StateService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StateServiceImpl extends GenericServiceImpl<State, Long> implements StateService {

    private final StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        super(stateRepository);
        this.stateRepository = stateRepository;
    }

    @Override
    public List<State> findByCountryId(Long countryId) {
        return stateRepository.findByCountryId(countryId);
    }
}
