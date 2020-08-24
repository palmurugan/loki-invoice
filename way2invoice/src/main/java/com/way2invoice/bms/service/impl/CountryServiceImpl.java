package com.way2invoice.bms.service.impl;

import com.way2invoice.bms.common.service.impl.GenericServiceImpl;
import com.way2invoice.bms.domain.Country;
import com.way2invoice.bms.repository.CountryRepository;
import com.way2invoice.bms.service.CountryService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * A service class for the domain {@link Country}
 */
@Service
public class CountryServiceImpl extends GenericServiceImpl<Country, Long> implements
    CountryService {

    private final CountryRepository repository;

    public CountryServiceImpl(CountryRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Country> findAll() {
        return repository.findAll();
    }
}
