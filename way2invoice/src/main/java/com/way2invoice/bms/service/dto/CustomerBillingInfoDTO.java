package com.way2invoice.bms.service.dto;

import com.way2invoice.bms.domain.enumeration.Status;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.way2invoice.bms.domain.CustomerBillingInfo} entity.
 */
public class CustomerBillingInfoDTO implements Serializable {

  private Long id;

  private String tin;

  private String gst;

  private String pan;

  private BigDecimal billCreditLimit;

  private BigDecimal creditLimit;

  private Status status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTin() {
    return tin;
  }

  public void setTin(String tin) {
    this.tin = tin;
  }

  public String getGst() {
    return gst;
  }

  public void setGst(String gst) {
    this.gst = gst;
  }

  public String getPan() {
    return pan;
  }

  public void setPan(String pan) {
    this.pan = pan;
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
    if (!(o instanceof CustomerBillingInfoDTO)) {
      return false;
    }

    return id != null && id.equals(((CustomerBillingInfoDTO) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  // prettier-ignore
  @Override
  public String toString() {
    return "CustomerBillingInfoDTO{" +
        "id=" + getId() +
        ", tin='" + getTin() + "'" +
        ", gst='" + getGst() + "'" +
        ", pan='" + getPan() + "'" +
        ", billCreditLimit=" + getBillCreditLimit() +
        ", creditLimit=" + getCreditLimit() +
        ", status='" + getStatus() + "'" +
        "}";
  }
}
