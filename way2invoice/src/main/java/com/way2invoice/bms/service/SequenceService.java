package com.way2invoice.bms.service;

import com.way2invoice.bms.domain.Sequence;
import com.way2invoice.bms.domain.enumeration.SequenceType;
import com.way2invoice.bms.repository.SequenceRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * A service class for the domain {@link com.way2invoice.bms.domain.Sequence}
 */
@Service
@Transactional
public class SequenceService {

  private static final Logger log = LoggerFactory.getLogger(SequenceService.class);

  private final SequenceRepository sequenceRepository;

  public SequenceService(SequenceRepository sequenceRepository) {
    this.sequenceRepository = sequenceRepository;
  }

  public Long nextVal(SequenceType type) {
    log.debug("Request to get the next sequence value for the type {}", type);
    Long nextVal = 0L;
    Optional<Sequence> sequence = sequenceRepository.findByType(type);
    if (sequence.isPresent()) {
      Sequence sequenceData = sequence.get();
      nextVal = sequenceData.getSequenceId();
      sequenceData.setSequenceId(nextVal + 1);
      sequenceRepository.save(sequenceData);
    }
    return nextVal;
  }
}
