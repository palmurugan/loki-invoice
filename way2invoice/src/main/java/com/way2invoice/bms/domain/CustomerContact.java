package com.way2invoice.bms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.way2invoice.bms.domain.enumeration.Status;

/**
 * A CustomerContact.
 */
@Entity
@Table(name = "customer_contact")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomerContact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @Size(max = 15)
    @Column(name = "state", length = 15, nullable = false)
    private String state;

    @NotNull
    @Size(max = 15)
    @Column(name = "country", length = 15, nullable = false)
    private String country;

    @NotNull
    @Size(max = 12)
    @Column(name = "zipcode", length = 12, nullable = false)
    private String zipcode;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties(value = "customerContacts", allowSetters = true)
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public CustomerContact address1(String address1) {
        this.address1 = address1;
        return this;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public CustomerContact address2(String address2) {
        this.address2 = address2;
        return this;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public CustomerContact city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public CustomerContact state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public CustomerContact country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public CustomerContact zipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Status getStatus() {
        return status;
    }

    public CustomerContact status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustomerContact customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomerContact)) {
            return false;
        }
        return id != null && id.equals(((CustomerContact) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomerContact{" +
            "id=" + getId() +
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
