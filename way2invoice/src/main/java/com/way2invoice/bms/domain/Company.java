package com.way2invoice.bms.domain;

import com.way2invoice.bms.domain.enumeration.Status;
import com.way2invoice.bms.domain.enumeration.TaxationType;
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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * A Company Domain
 */
@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "city", nullable = false)
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
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull
    @Column(name = "contact_name", nullable = false)
    private String contactName;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "gstin")
    private String gstNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tax_type", nullable = false)
    private TaxationType taxationType;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public TaxationType getTaxationType() {
        return taxationType;
    }

    public void setTaxationType(TaxationType taxationType) {
        this.taxationType = taxationType;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Company company = (Company) o;

        return new EqualsBuilder()
            .append(id, company.id)
            .append(name, company.name)
            .append(city, company.city)
            .append(state, company.state)
            .append(country, company.country)
            .append(address, company.address)
            .append(contactName, company.contactName)
            .append(phone, company.phone)
            .append(email, company.email)
            .append(gstNumber, company.gstNumber)
            .append(taxationType, company.taxationType)
            .append(currency, company.currency)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(id)
            .append(name)
            .append(city)
            .append(state)
            .append(country)
            .append(address)
            .append(contactName)
            .append(phone)
            .append(email)
            .append(gstNumber)
            .append(taxationType)
            .append(currency)
            .toHashCode();
    }

    @Override
    public String toString() {
        return "Company{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", city='" + city + '\'' +
            ", state=" + state +
            ", country=" + country +
            ", address='" + address + '\'' +
            ", contactName='" + contactName + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            ", gstNumber='" + gstNumber + '\'' +
            ", taxationType=" + taxationType +
            ", currency=" + currency +
            '}';
    }
}
