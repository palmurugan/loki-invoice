package com.way2invoice.bms.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link com.way2invoice.bms.domain.InvoiceLine} entity.
 */
public class InvoiceLineDTO implements Serializable {

    private Long id;

    @NotNull
    private Long itemId;

    @NotNull
    private Double quantity;

    @NotNull
    private BigDecimal price;

    private Double taxRate;

    private BigDecimal discount;

    private Boolean isPercentage;

    private Long invoiceId;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvoiceLineDTO)) {
            return false;
        }

        return id != null && id.equals(((InvoiceLineDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "InvoiceLineDTO{" +
            "id=" + id +
            ", itemId=" + itemId +
            ", quantity=" + quantity +
            ", price=" + price +
            ", taxRate=" + taxRate +
            ", discount=" + discount +
            ", isPercentage=" + isPercentage +
            ", invoiceId=" + invoiceId +
            '}';
    }
}
