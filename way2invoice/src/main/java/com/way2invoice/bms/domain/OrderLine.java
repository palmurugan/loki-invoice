package com.way2invoice.bms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.way2invoice.bms.common.auditing.domain.Auditable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author palmuruganc
 * <p>
 * A OrderLine Domain
 */
@Entity
@Table(name = "order_line")
public class OrderLine extends Auditable<String> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(name = "quantity", nullable = false)
  private Double quantity;

  @NotNull
  @Column(name = "price", precision = 21, scale = 2, nullable = false)
  private BigDecimal price;

  @Column(name = "discount", precision = 21, scale = 2)
  private BigDecimal discount;

  @Column(name = "is_percentage")
  private Boolean isPercentage;

  @OneToOne
  @JoinColumn(unique = true)
  private Item item;

  @ManyToOne
  @JsonIgnoreProperties(value = "orderLines", allowSetters = true)
  private PurchaseOrder purchaseOrder;

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

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public PurchaseOrder getPurchaseOrder() {
    return purchaseOrder;
  }

  public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }
}
