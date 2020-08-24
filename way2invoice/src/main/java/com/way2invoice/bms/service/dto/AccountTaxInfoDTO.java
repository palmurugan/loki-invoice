package com.way2invoice.bms.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.way2invoice.bms.domain.AccountTaxInfo} entity.
 */
public class AccountTaxInfoDTO implements Serializable {
    
    private Long id;

    private String tin;

    private String gst;

    private String pan;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountTaxInfoDTO)) {
            return false;
        }

        return id != null && id.equals(((AccountTaxInfoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AccountTaxInfoDTO{" +
            "id=" + getId() +
            ", tin='" + getTin() + "'" +
            ", gst='" + getGst() + "'" +
            ", pan='" + getPan() + "'" +
            "}";
    }
}
