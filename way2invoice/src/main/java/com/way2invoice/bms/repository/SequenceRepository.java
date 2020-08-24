package com.way2invoice.bms.repository;

import com.way2invoice.bms.domain.Sequence;
import com.way2invoice.bms.domain.enumeration.SequenceType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The repository for the domain {@link Sequence}
 */
@Repository
public interface SequenceRepository extends JpaRepository<Sequence, Long> {

  Optional<Sequence> findByType(SequenceType type);
}
