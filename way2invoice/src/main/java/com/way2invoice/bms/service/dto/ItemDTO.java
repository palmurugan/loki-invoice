package com.way2invoice.bms.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link com.way2invoice.bms.domain.Item} entity.
 */
@JsonInclude(Include.NON_NULL)
public class ItemDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 32)
    private String name;

    @NotNull
    @Size(max = 16)
    private String code;

    @NotNull
    @Size(max = 300)
    private String description;

    private Long itemPriceId;

    @NotNull(message = "Price is required")
    private BigDecimal itemPrice;

    @NotNull(message = "Type is required")
    private Long itemTypeId;

    @NotNull(message = "Unit is required")
    private Long unitId;

    private String unit;

    private Long taxId;

    private String taxName;

    private Double taxValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getItemPriceId() {
        return itemPriceId;
    }

    public void setItemPriceId(Long itemPriceId) {
        this.itemPriceId = itemPriceId;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public Double getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(Double taxValue) {
        this.taxValue = taxValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemDTO)) {
            return false;
        }

        return id != null && id.equals(((ItemDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", code='" + code + '\'' +
            ", description='" + description + '\'' +
            ", itemPriceId=" + itemPriceId +
            ", itemPrice=" + itemPrice +
            ", itemTypeId=" + itemTypeId +
            ", unitId=" + unitId +
            ", unit='" + unit + '\'' +
            ", taxId=" + taxId +
            ", taxName='" + taxName + '\'' +
            '}';
    }
}
