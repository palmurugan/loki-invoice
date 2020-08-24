package com.way2invoice.bms.service.dto;

import com.way2invoice.bms.domain.enumeration.InvoiceType;
import com.way2invoice.bms.domain.enumeration.Status;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link com.way2invoice.bms.domain.Invoice} entity.
 */
public class InvoiceDTO implements Serializable {

  private Long id;

  private String name;

  @NotNull
  private InvoiceType type;

  private Long accountId;

  private String accountName;

  @NotNull
  private LocalDate date;

  @NotNull
  private LocalDate dueDate;

  private BigDecimal total;

  private BigDecimal balance;

  private BigDecimal carriedBalance;

  private BigDecimal discount;

  private Boolean isPercentage;

  private String notes;

  private Status status;

  private Set<InvoiceLineDTO> invoiceLines = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public BigDecimal getCarriedBalance() {
    return carriedBalance;
  }

  public void setCarriedBalance(BigDecimal carriedBalance) {
    this.carriedBalance = carriedBalance;
  }

  public BigDecimal getDiscount() {
    return discount;
  }

  public void setDiscount(BigDecimal discount) {
    this.discount = discount;
  }

  public Boolean isIsPercentage() {
    return isPercentage;
  }

  public void setIsPercentage(Boolean isPercentage) {
    this.isPercentage = isPercentage;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Set<InvoiceLineDTO> getInvoiceLines() {
    return invoiceLines;
  }

  public void setInvoiceLines(Set<InvoiceLineDTO> invoiceLines) {
    this.invoiceLines = invoiceLines;
  }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public InvoiceType getType() {
    return type;
  }

  public void setType(InvoiceType type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof InvoiceDTO)) {
      return false;
    }

    return id != null && id.equals(((InvoiceDTO) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "InvoiceDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", type=" + type +
            ", accountId=" + accountId +
            ", accountName='" + accountName + '\'' +
            ", date=" + date +
            ", dueDate=" + dueDate +
            ", total=" + total +
            ", balance=" + balance +
            ", carriedBalance=" + carriedBalance +
            ", discount=" + discount +
            ", isPercentage=" + isPercentage +
            ", notes='" + notes + '\'' +
            ", status=" + status +
            ", invoiceLines=" + invoiceLines +
            '}';
    }
}
