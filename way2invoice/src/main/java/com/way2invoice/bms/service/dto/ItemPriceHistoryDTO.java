package com.way2invoice.bms.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.way2invoice.bms.domain.ItemPriceHistory} entity.
 */
public class ItemPriceHistoryDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long itemId;

    @NotNull
    private BigDecimal price;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemPriceHistoryDTO)) {
            return false;
        }

        return id != null && id.equals(((ItemPriceHistoryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemPriceHistoryDTO{" +
            "id=" + getId() +
            ", itemId=" + getItemId() +
            ", price=" + getPrice() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
}
