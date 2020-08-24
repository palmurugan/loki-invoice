package com.way2invoice.bms.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;

import com.way2invoice.bms.domain.enumeration.Status;

/**
 * A CustomerBillingInfo.
 */
@Entity
@Table(name = "customer_billing_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomerBillingInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tin")
    private String tin;

    @Column(name = "gst")
    private String gst;

    @Column(name = "pan")
    private String pan;

    @Column(name = "bill_credit_limit", precision = 21, scale = 2)
    private BigDecimal billCreditLimit;

    @Column(name = "credit_limit", precision = 21, scale = 2)
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

    public String getTin() {
        return tin;
    }

    public CustomerBillingInfo tin(String tin) {
        this.tin = tin;
        return this;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getGst() {
        return gst;
    }

    public CustomerBillingInfo gst(String gst) {
        this.gst = gst;
        return this;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getPan() {
        return pan;
    }

    public CustomerBillingInfo pan(String pan) {
        this.pan = pan;
        return this;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public BigDecimal getBillCreditLimit() {
        return billCreditLimit;
    }

    public CustomerBillingInfo billCreditLimit(BigDecimal billCreditLimit) {
        this.billCreditLimit = billCreditLimit;
        return this;
    }

    public void setBillCreditLimit(BigDecimal billCreditLimit) {
        this.billCreditLimit = billCreditLimit;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public CustomerBillingInfo creditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
        return this;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Status getStatus() {
        return status;
    }

    public CustomerBillingInfo status(Status status) {
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
        if (!(o instanceof CustomerBillingInfo)) {
            return false;
        }
        return id != null && id.equals(((CustomerBillingInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomerBillingInfo{" +
            "id=" + getId() +
            ", tin='" + getTin() + "'" +
            ", gst='" + getGst() + "'" +
            ", pan='" + getPan() + "'" +
            ", billCreditLimit=" + getBillCreditLimit() +
            ", creditLimit=" + getCreditLimit() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
