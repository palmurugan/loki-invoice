package com.way2invoice.bms.service.dto;

import com.way2invoice.bms.domain.enumeration.Status;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link com.way2invoice.bms.domain.Currency} entity.
 */
public class CurrencyDTO implements Serializable {

  private Long id;

  @NotNull
  private Long clientId;

  @NotNull
  private String name;

  @NotNull
  private String code;

  private Status status = Status.ACTIVE;

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

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    if (status == null) {
      this.status = Status.ACTIVE;
    } else {
      this.status = status;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CurrencyDTO)) {
      return false;
    }

    return id != null && id.equals(((CurrencyDTO) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  // prettier-ignore
  @Override
  public String toString() {
    return "CurrencyDTO{" +
        "id=" + getId() +
        ", clientId=" + getClientId() +
        ", name='" + getName() + "'" +
        ", code='" + getCode() + "'" +
        ", status='" + getStatus() + "'" +
        "}";
  }
}
