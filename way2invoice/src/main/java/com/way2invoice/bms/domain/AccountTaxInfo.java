package com.way2invoice.bms.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A AccountTaxInfo.
 */
@Entity
@Table(name = "account_tax_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AccountTaxInfo implements Serializable {

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

    public AccountTaxInfo tin(String tin) {
        this.tin = tin;
        return this;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getGst() {
        return gst;
    }

    public AccountTaxInfo gst(String gst) {
        this.gst = gst;
        return this;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getPan() {
        return pan;
    }

    public AccountTaxInfo pan(String pan) {
        this.pan = pan;
        return this;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountTaxInfo)) {
            return false;
        }
        return id != null && id.equals(((AccountTaxInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AccountTaxInfo{" +
            "id=" + getId() +
            ", tin='" + getTin() + "'" +
            ", gst='" + getGst() + "'" +
            ", pan='" + getPan() + "'" +
            "}";
    }
}
