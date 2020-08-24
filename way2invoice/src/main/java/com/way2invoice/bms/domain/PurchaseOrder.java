package com.way2invoice.bms.domain;

import com.way2invoice.bms.common.auditing.domain.Auditable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author palmuruganc
 * <p>
 * A PurchaseOrder domain
 */
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder extends Auditable<String> implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "billing_type_id")
  private BillingType billingType;

  @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
  private Set<OrderLine> orderLines = new HashSet<>();

  @NotNull
  @Column(name = "active_since", nullable = false)
  private LocalDate activeSince;

  @Column(name = "active_until")
  private LocalDate activeUntil;

  @Column(name = "cycle_start")
  private LocalDate cycleStart;

  @Column(name = "next_billable_day")
  private LocalDate nextBillableDay;

  @ManyToOne
  @JoinColumn(name = "currency_id")
  private Currency currency;

  @Column(name = "notify")
  private Boolean notify;

  @Column(name = "due_date")
  private LocalDate dueDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public BillingType getBillingType() {
    return billingType;
  }

  public void setBillingType(BillingType billingType) {
    this.billingType = billingType;
  }

  public Set<OrderLine> getOrderLines() {
    return orderLines;
  }

  public void setOrderLines(Set<OrderLine> orderLines) {
    for(OrderLine orderLine: orderLines) {
      orderLine.setPurchaseOrder(this);
    }
    this.orderLines = orderLines;
  }

  public LocalDate getActiveSince() {
    return activeSince;
  }

  public void setActiveSince(LocalDate activeSince) {
    this.activeSince = activeSince;
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

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
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
}
