package com.way2invoice.bms.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.way2invoice.bms.domain.enumeration.Status;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link com.way2invoice.bms.domain.BillingPeriod} entity.
 */
public class BillingPeriodDTO implements Serializable {

  private Long id;

  @NotNull
  private Long clientId;

  @NotNull
  private String name;

  private String type;

  private Status status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getClientId() {
    return clientId;
  }

  public void setClientId(Long clientId) {
    this.clientId = clientId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BillingPeriodDTO)) {
      return false;
    }

    return id != null && id.equals(((BillingPeriodDTO) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  // prettier-ignore
  @Override
  public String toString() {
    return "BillingPeriodDTO{" +
        "id=" + getId() +
        ", clientId=" + getClientId() +
        ", name='" + getName() + "'" +
        ", type='" + getType() + "'" +
        ", status='" + getStatus() + "'" +
        "}";
  }
}
