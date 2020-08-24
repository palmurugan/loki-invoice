package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.State;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A spring boot repository for the domain {@link State}
 */
@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    List<State> findByCountryId(Long countryId);
}
