package com.way2invoice.bms.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.way2invoice.bms.domain.enumeration.Status;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link com.way2invoice.bms.domain.Customer} entity.
 */
public class CustomerDTO implements Serializable {

  private Long id;

  @JsonIgnore
  private Long clientId;

  @NotNull
  @Size(max = 32)
  private String name;

  @NotNull
  private String company;

  @NotNull
  private Status status;

  @NotNull(message = "Customer category is required")
  private Long customerCategoryId;

  private String categoryName;

  @Valid
  private CustomerBillingInfoDTO customerBillingInfo;

  @Valid
  private Set<CustomerContactDTO> customerContacts = new HashSet<>();

  private Long currencyId;

  private String currency;

  private Long billingTypeId;

  private String billingType;

  private Long billingPeriodId;

  private String billingPeriod;

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

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
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

  public CustomerBillingInfoDTO getCustomerBillingInfo() {
    return customerBillingInfo;
  }

  public void setCustomerBillingInfo(
      CustomerBillingInfoDTO customerBillingInfo) {
    this.customerBillingInfo = customerBillingInfo;
  }

  public Set<CustomerContactDTO> getCustomerContacts() {
    return customerContacts;
  }

  public void setCustomerContacts(
      Set<CustomerContactDTO> customerContacts) {
    this.customerContacts = customerContacts;
  }

  public Long getCustomerCategoryId() {
    return customerCategoryId;
  }

  public void setCustomerCategoryId(Long customerCategoryId) {
    this.customerCategoryId = customerCategoryId;
  }

  public Long getCurrencyId() {
    return currencyId;
  }

  public void setCurrencyId(Long currencyId) {
    this.currencyId = currencyId;
  }

  public Long getBillingTypeId() {
    return billingTypeId;
  }

  public void setBillingTypeId(Long billingTypeId) {
    this.billingTypeId = billingTypeId;
  }

  public Long getBillingPeriodId() {
    return billingPeriodId;
  }

  public void setBillingPeriodId(Long billingPeriodId) {
    this.billingPeriodId = billingPeriodId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getBillingType() {
    return billingType;
  }

  public void setBillingType(String billingType) {
    this.billingType = billingType;
  }

  public String getBillingPeriod() {
    return billingPeriod;
  }

  public void setBillingPeriod(String billingPeriod) {
    this.billingPeriod = billingPeriod;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CustomerDTO)) {
      return false;
    }

    return id != null && id.equals(((CustomerDTO) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return "CustomerDTO{" +
        "id=" + id +
        ", clientId=" + clientId +
        ", name='" + name + '\'' +
        ", company='" + company + '\'' +
        ", status=" + status +
        ", customerCategoryId=" + customerCategoryId +
        ", categoryName='" + categoryName + '\'' +
        ", customerBillingInfo=" + customerBillingInfo +
        ", customerContacts=" + customerContacts +
        ", currencyId=" + currencyId +
        ", currency='" + currency + '\'' +
        ", billingTypeId=" + billingTypeId +
        ", billingType='" + billingType + '\'' +
        ", billingPeriodId=" + billingPeriodId +
        ", billingPeriod='" + billingPeriod + '\'' +
        '}';
  }
}
