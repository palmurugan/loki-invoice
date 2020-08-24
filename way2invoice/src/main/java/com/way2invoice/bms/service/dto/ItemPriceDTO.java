package com.way2invoice.bms.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import com.way2invoice.bms.domain.enumeration.Status;

/**
 * A DTO for the {@link com.way2invoice.bms.domain.ItemPrice} entity.
 */
public class ItemPriceDTO implements Serializable {
    
    private Long id;

    @NotNull
    private BigDecimal price;

    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
        if (!(o instanceof ItemPriceDTO)) {
            return false;
        }

        return id != null && id.equals(((ItemPriceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemPriceDTO{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
