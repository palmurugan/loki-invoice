package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.Country;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring data repository for the domain {@link Country}
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findAll();

}
