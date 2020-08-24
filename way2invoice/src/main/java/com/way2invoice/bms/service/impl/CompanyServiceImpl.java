package com.way2invoice.bms.service.impl;

import com.way2invoice.bms.common.service.impl.GenericServiceImpl;
import com.way2invoice.bms.domain.Company;
import com.way2invoice.bms.repository.CompanyRepository;
import com.way2invoice.bms.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyServiceImpl extends GenericServiceImpl<Company, Long> implements
    CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        super(companyRepository);
        this.companyRepository = companyRepository;
    }
}
