package com.way2invoice.bms.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;

import com.way2invoice.bms.domain.enumeration.Status;

/**
 * A CustomerCategory.
 */
@Entity
@Table(name = "customer_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomerCategory implements Serializable {

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
    @Column(name = "bill_credit_limit", precision = 21, scale = 2, nullable = false)
    private BigDecimal billCreditLimit;

    @NotNull
    @Column(name = "credit_limit", precision = 21, scale = 2, nullable = false)
    private BigDecimal creditLimit;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

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

    public CustomerCategory clientId(Long clientId) {
        this.clientId = clientId;
        return this;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public CustomerCategory name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBillCreditLimit() {
        return billCreditLimit;
    }

    public CustomerCategory billCreditLimit(BigDecimal billCreditLimit) {
        this.billCreditLimit = billCreditLimit;
        return this;
    }

    public void setBillCreditLimit(BigDecimal billCreditLimit) {
        this.billCreditLimit = billCreditLimit;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public CustomerCategory creditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
        return this;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Status getStatus() {
        return status;
    }

    public CustomerCategory status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomerCategory)) {
            return false;
        }
        return id != null && id.equals(((CustomerCategory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomerCategory{" +
            "id=" + getId() +
            ", clientId=" + getClientId() +
            ", name='" + getName() + "'" +
            ", billCreditLimit=" + getBillCreditLimit() +
            ", creditLimit=" + getCreditLimit() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
