package com.way2invoice.bms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.way2invoice.bms.domain.enumeration.Status;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AccountContact.
 */
@Entity
@Table(name = "account_contact")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AccountContact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotNull
    @Column(name = "address_1", nullable = false)
    private String address1;

    @Column(name = "address_2")
    private String address2;

    @NotNull
    @Size(max = 32)
    @Column(name = "city", length = 32, nullable = false)
    private String city;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @NotNull
    @Size(max = 12)
    @Column(name = "zipcode", length = 12, nullable = false)
    private String zipcode;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToOne
    @JsonIgnoreProperties(value = "accountContacts", allowSetters = true)
    private Accounts accounts;

    // jhipster-needle-entity-add-field - JHipster will add fields here
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

    public AccountContact phone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public AccountContact address1(String address1) {
        this.address1 = address1;
        return this;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public AccountContact address2(String address2) {
        this.address2 = address2;
        return this;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public AccountContact city(String city) {
        this.city = city;
        return this;
    }

    public AccountContact state(State state) {
        this.state = state;
        return this;
    }

    public AccountContact country(Country country) {
        this.country = country;
        return this;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public AccountContact zipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public AccountContact status(Status status) {
        this.status = status;
        return this;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public AccountContact accounts(Accounts accounts) {
        this.accounts = accounts;
        return this;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountContact)) {
            return false;
        }
        return id != null && id.equals(((AccountContact) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AccountContact{" +
            "id=" + getId() +
            ", phone='" + getPhone() + "'" +
            ", address1='" + getAddress1() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", country='" + getCountry() + "'" +
            ", zipcode='" + getZipcode() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
