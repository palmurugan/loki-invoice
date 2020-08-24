package com.way2invoice.bms.service.dto;

import com.way2invoice.bms.domain.enumeration.Status;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link com.way2invoice.bms.domain.AccountContact} entity.
 */
public class AccountContactDTO implements Serializable {

    private Long id;

    @NotNull
    private String phone;

    @NotNull
    private String address1;

    private String address2;

    @NotNull
    @Size(max = 32)
    private String city;

    @NotNull
    private Long stateId;

    @NotNull
    private Long countryId;

    @NotNull
    @Size(max = 12)
    private String zipcode;

    @NotNull
    private Status status;

    private Long accountsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(Long accountsId) {
        this.accountsId = accountsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountContactDTO)) {
            return false;
        }

        return id != null && id.equals(((AccountContactDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "AccountContactDTO{" +
            "id=" + id +
            ", phone='" + phone + '\'' +
            ", address1='" + address1 + '\'' +
            ", address2='" + address2 + '\'' +
            ", city='" + city + '\'' +
            ", stateId=" + stateId +
            ", countryId=" + countryId +
            ", zipcode='" + zipcode + '\'' +
            ", status=" + status +
            ", accountsId=" + accountsId +
            '}';
    }
}
