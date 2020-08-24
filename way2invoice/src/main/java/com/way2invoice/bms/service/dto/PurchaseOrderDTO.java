package com.way2invoice.bms.service.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link com.way2invoice.bms.domain.PurchaseOrder} entity
 */
public class PurchaseOrderDTO {

  private Long id;

  @NotNull(message = "customer is required")
  private Long customerId;

  @NotNull(message = "billing type is required")
  private Long billingTypeId;

  @NotNull
  private LocalDate activeSince;

  @NotNull
  private Long currencyId;

  private LocalDate activeUntil;

  private LocalDate cycleStart;

  private LocalDate nextBillableDay;

  private Boolean notify;

  private LocalDate dueDate;

  private Set<OrderLineDTO> orderLines = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public Long getBillingTypeId() {
    return billingTypeId;
  }

  public void setBillingTypeId(Long billingTypeId) {
    this.billingTypeId = billingTypeId;
  }

  public LocalDate getActiveSince() {
    return activeSince;
  }

  public void setActiveSince(LocalDate activeSince) {
    this.activeSince = activeSince;
  }

  public Long getCurrencyId() {
    return currencyId;
  }

  public void setCurrencyId(Long currencyId) {
    this.currencyId = currencyId;
  }

  public LocalDate getActiveUntil() {
    return activeUntil;
  }

  public void setActiveUntil(LocalDate activeUntil) {
    this.activeUntil = activeUntil;
  }

  public LocalDate getCycleStart() {
    return cycleStart;
  }

  public void setCycleStart(LocalDate cycleStart) {
    this.cycleStart = cycleStart;
  }

  public LocalDate getNextBillableDay() {
    return nextBillableDay;
  }

  public void setNextBillableDay(LocalDate nextBillableDay) {
    this.nextBillableDay = nextBillableDay;
  }

  public Boolean getNotify() {
    return notify;
  }

  public void setNotify(Boolean notify) {
    this.notify = notify;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public Set<OrderLineDTO> getOrderLines() {
    return orderLines;
  }

  public void setOrderLines(Set<OrderLineDTO> orderLines) {
    this.orderLines = orderLines;
  }
}
