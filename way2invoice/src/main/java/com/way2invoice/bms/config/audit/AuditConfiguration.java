package com.way2invoice.bms.config.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Entity level audit related configuration
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditConfiguration {

  @Bean
  public AuditorAware<String> auditorAware() {
    return new AuditorAwareImpl();
  }
}
