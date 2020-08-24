package com.way2invoice.bms.domain;

import com.way2invoice.bms.common.auditing.domain.Auditable;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ItemPrice.
 */
@Entity
@Table(name = "item_price")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ItemPrice extends Auditable<String> implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(name = "price", precision = 21, scale = 2, nullable = false)
  private BigDecimal price;

  // jhipster-needle-entity-add-field - JHipster will add fields here
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

  public ItemPrice price(BigDecimal price) {
    this.price = price;
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ItemPrice)) {
      return false;
    }
    return id != null && id.equals(((ItemPrice) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return "ItemPrice{" +
        "id=" + id +
        ", price=" + price +
        '}';
  }
}
