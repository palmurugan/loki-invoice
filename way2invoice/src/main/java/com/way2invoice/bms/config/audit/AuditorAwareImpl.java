package com.way2invoice.bms.config.audit;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;

/**
 * Auditing implementation class
 */
public class AuditorAwareImpl implements AuditorAware {

  /**
   * This method will return the current owner of each request.
   *
   * @return The current auditor
   */
  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.of("Admin");
  }
}
