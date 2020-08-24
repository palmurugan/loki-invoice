package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A Repository for the domain {@link Company}
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
