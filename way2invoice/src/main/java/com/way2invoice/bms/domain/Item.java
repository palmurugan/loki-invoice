package com.way2invoice.bms.domain;

import com.way2invoice.bms.common.auditing.domain.Auditable;
import java.io.Serializable;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Item.
 */
@Entity
@Table(name = "item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Item extends Auditable<String> implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(name = "client_id", nullable = false)
  private Long clientId;

  @NotNull
  @Size(max = 32)
  @Column(name = "name", length = 32, nullable = false)
  private String name;

  @NotNull
  @Size(max = 16)
  @Column(name = "code", length = 16, nullable = false)
  private String code;

  @NotNull
  @Size(max = 300)
  @Column(name = "description", length = 300, nullable = false)
  private String description;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(unique = true)
  private ItemPrice itemPrice;

  @ManyToOne
  @JoinColumn(name = "item_type_id")
  private ItemType itemType;

  @ManyToOne
  @JoinColumn(name = "unit_id")
  private Unit unit;

  @ManyToOne
  @JoinColumn(name = "tax_id")
  private Tax tax;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getClientId() {
    return clientId;
  }

  public void setClientId(Long clientId) {
    this.clientId = clientId;
  }

  public Item clientId(Long clientId) {
    this.clientId = clientId;
    return this;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Item name(String name) {
    this.name = name;
    return this;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Item code(String code) {
    this.code = code;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Item description(String description) {
    this.description = description;
    return this;
  }

  public ItemPrice getItemPrice() {
    return itemPrice;
  }

  public void setItemPrice(ItemPrice itemPrice) {
    this.itemPrice = itemPrice;
  }

  public Item itemPrice(ItemPrice itemPrice) {
    this.itemPrice = itemPrice;
    return this;
  }

  public ItemType getItemType() {
    return itemType;
  }

  public void setItemType(ItemType itemType) {
    this.itemType = itemType;
  }

  public Item itemType(ItemType itemType) {
    this.itemType = itemType;
    return this;
  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

  public Item unit(Unit unit) {
    this.unit = unit;
    return this;
  }

  public Tax getTax() {
    return tax;
  }

  public void setTax(Tax tax) {
    this.tax = tax;
  }

  public Item tax(Tax tax) {
    this.tax = tax;
    return this;
  }
  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Item)) {
      return false;
    }
    return id != null && id.equals(((Item) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  // prettier-ignore
  @Override
  public String toString() {
    return "Item{" +
        "id=" + id +
        ", clientId=" + clientId +
        ", name='" + name + '\'' +
        ", code='" + code + '\'' +
        ", description='" + description + '\'' +
        ", itemPrice=" + itemPrice +
        ", itemType=" + itemType +
        ", unit=" + unit +
        ", tax=" + tax +
        '}';
  }
}
