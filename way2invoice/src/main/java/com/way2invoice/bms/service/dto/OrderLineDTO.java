package com.way2invoice.bms.service.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

/**
 * @author palmuruganc
 * <p>
 * A DTO for the {@link com.way2invoice.bms.domain.OrderLine} entity
 */
public class OrderLineDTO {

  private Long id;

  @NotNull
  private Double quantity;

  @NotNull
  private BigDecimal price;

  private BigDecimal discount;

  private Boolean isPercentage;

  private Long itemId;

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

  public Boolean getPercentage() {
    return isPercentage;
  }

  public void setPercentage(Boolean percentage) {
    isPercentage = percentage;
  }

  public Long getItemId() {
    return itemId;
  }

  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }
}
