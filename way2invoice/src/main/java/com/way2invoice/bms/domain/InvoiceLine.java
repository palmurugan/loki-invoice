package com.way2invoice.bms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A InvoiceLine.
 */
@Entity
@Table(name = "invoice_line")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InvoiceLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Double quantity;

    @NotNull
    @Column(name = "price", precision = 21, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "tax_rate")
    private Double taxRate;

    @Column(name = "discount", precision = 21, scale = 2)
    private BigDecimal discount;

    @Column(name = "is_percentage")
    private Boolean isPercentage;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JsonIgnoreProperties(value = "invoiceLines", allowSetters = true)
    private Invoice invoice;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public InvoiceLine quantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public InvoiceLine price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public InvoiceLine discount(BigDecimal discount) {
        this.discount = discount;
        return this;
    }

    public Boolean isIsPercentage() {
        return isPercentage;
    }

    public InvoiceLine isPercentage(Boolean isPercentage) {
        this.isPercentage = isPercentage;
        return this;
    }

    public void setIsPercentage(Boolean isPercentage) {
        this.isPercentage = isPercentage;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public InvoiceLine item(Item item) {
        this.item = item;
        return this;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public InvoiceLine invoice(Invoice invoice) {
        this.invoice = invoice;
        return this;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvoiceLine)) {
            return false;
        }
        return id != null && id.equals(((InvoiceLine) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvoiceLine{" +
            "id=" + id +
            ", quantity=" + quantity +
            ", price=" + price +
            ", taxRate=" + taxRate +
            ", discount=" + discount +
            ", isPercentage=" + isPercentage +
            ", item=" + item +
            ", invoice=" + invoice +
            '}';
    }
}
