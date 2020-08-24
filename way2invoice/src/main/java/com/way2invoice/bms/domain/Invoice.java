package com.way2invoice.bms.domain;

import com.way2invoice.bms.common.auditing.domain.Auditable;
import com.way2invoice.bms.domain.enumeration.InvoiceType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Invoice.
 */
@Entity
@Table(name = "invoice")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Invoice extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private InvoiceType type;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @NotNull
    @Column(name = "total", precision = 21, scale = 2, nullable = false)
    private BigDecimal total;

    @Column(name = "balance", precision = 21, scale = 2)
    private BigDecimal balance;

    @Column(name = "carried_balance", precision = 21, scale = 2)
    private BigDecimal carriedBalance;

    @Column(name = "discount", precision = 21, scale = 2)
    private BigDecimal discount;

    @Column(name = "is_percentage")
    private Boolean isPercentage;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Accounts account;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<InvoiceLine> invoiceLines = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
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

    public Invoice date(LocalDate date) {
        this.date = date;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Invoice dueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Invoice total(BigDecimal total) {
        this.total = total;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Invoice balance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public BigDecimal getCarriedBalance() {
        return carriedBalance;
    }

    public void setCarriedBalance(BigDecimal carriedBalance) {
        this.carriedBalance = carriedBalance;
    }

    public Invoice carriedBalance(BigDecimal carriedBalance) {
        this.carriedBalance = carriedBalance;
        return this;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Invoice discount(BigDecimal discount) {
        this.discount = discount;
        return this;
    }

    public Boolean isIsPercentage() {
        return isPercentage;
    }

    public Invoice isPercentage(Boolean isPercentage) {
        this.isPercentage = isPercentage;
        return this;
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

    public Invoice notes(String notes) {
        this.notes = notes;
        return this;
    }

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    public Invoice account(Accounts account) {
        this.account = account;
        return this;
    }

    public Set<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(Set<InvoiceLine> invoiceLines) {
        for (InvoiceLine invoiceLine : invoiceLines) {
            invoiceLine.setInvoice(this);
        }
        this.invoiceLines = invoiceLines;
    }

    public Invoice invoiceLines(Set<InvoiceLine> invoiceLines) {
        for (InvoiceLine invoiceLine : invoiceLines) {
            invoiceLine.setInvoice(this);
        }
        this.invoiceLines = invoiceLines;
        return this;
    }

    public Invoice addInvoiceLine(InvoiceLine invoiceLine) {
        this.invoiceLines.add(invoiceLine);
        invoiceLine.setInvoice(this);
        return this;
    }

    public Invoice removeInvoiceLine(InvoiceLine invoiceLine) {
        this.invoiceLines.remove(invoiceLine);
        invoiceLine.setInvoice(null);
        return this;
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

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Invoice)) {
            return false;
        }
        return id != null && id.equals(((Invoice) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "Invoice{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", type=" + type +
            ", date=" + date +
            ", dueDate=" + dueDate +
            ", total=" + total +
            ", balance=" + balance +
            ", carriedBalance=" + carriedBalance +
            ", discount=" + discount +
            ", isPercentage=" + isPercentage +
            ", notes='" + notes + '\'' +
            ", customer=" + account +
            ", invoiceLines=" + invoiceLines +
            '}';
    }
}
