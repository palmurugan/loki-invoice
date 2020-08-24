package com.way2invoice.bms.service.dto;

import com.way2invoice.bms.domain.enumeration.Status;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link com.way2invoice.bms.domain.CustomerCategory} entity.
 */
public class CustomerCategoryDTO implements Serializable {

  private Long id;

  @NotNull
  private Long clientId;

  @NotNull
  @Size(max = 32)
  private String name;

  @NotNull
  private BigDecimal billCreditLimit;

  @NotNull
  private BigDecimal creditLimit;

  @NotNull
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

  public BigDecimal getBillCreditLimit() {
    return billCreditLimit;
  }

  public void setBillCreditLimit(BigDecimal billCreditLimit) {
    this.billCreditLimit = billCreditLimit;
  }

  public BigDecimal getCreditLimit() {
    return creditLimit;
  }

  public void setCreditLimit(BigDecimal creditLimit) {
    this.creditLimit = creditLimit;
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
    if (!(o instanceof CustomerCategoryDTO)) {
      return false;
    }

    return id != null && id.equals(((CustomerCategoryDTO) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  // prettier-ignore
  @Override
  public String toString() {
    return "CustomerCategoryDTO{" +
        "id=" + getId() +
        ", clientId=" + getClientId() +
        ", name='" + getName() + "'" +
        ", billCreditLimit=" + getBillCreditLimit() +
        ", creditLimit=" + getCreditLimit() +
        ", status='" + getStatus() + "'" +
        "}";
  }
}
