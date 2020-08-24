package com.way2invoice.bms.domain;

import com.way2invoice.bms.common.auditing.domain.Auditable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.way2invoice.bms.domain.enumeration.Status;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Customer extends Auditable<String> implements Serializable {

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
    @Column(name = "company", nullable = false)
    private String company;

    @ManyToOne
    @JoinColumn(name = "customer_category_id")
    private CustomerCategory customerCategory;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    private CustomerBillingInfo customerBillingInfo;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "billing_type_id")
    private BillingType billingType;

    @ManyToOne
    @JoinColumn(name = "billing_period_id")
    private BillingPeriod billingPeriod;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CustomerContact> customerContacts = new HashSet<>();

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

    public Customer clientId(Long clientId) {
        this.clientId = clientId;
        return this;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public Customer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public Customer company(String company) {
        this.company = company;
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public CustomerCategory getCustomerCategory() {
        return customerCategory;
    }

    public Customer customerCategory(CustomerCategory customerCategory) {
        this.customerCategory = customerCategory;
        return this;
    }

    public void setCustomerCategory(CustomerCategory customerCategory) {
        this.customerCategory = customerCategory;
    }

    public CustomerBillingInfo getCustomerBillingInfo() {
        return customerBillingInfo;
    }

    public Customer customerBillingInfo(CustomerBillingInfo customerBillingInfo) {
        this.customerBillingInfo = customerBillingInfo;
        return this;
    }

    public void setCustomerBillingInfo(CustomerBillingInfo customerBillingInfo) {
        this.customerBillingInfo = customerBillingInfo;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Customer currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BillingType getBillingType() {
        return billingType;
    }

    public Customer billingType(BillingType billingType) {
        this.billingType = billingType;
        return this;
    }

    public void setBillingType(BillingType billingType) {
        this.billingType = billingType;
    }

    public BillingPeriod getBillingPeriod() {
        return billingPeriod;
    }

    public Customer billingPeriod(BillingPeriod billingPeriod) {
        this.billingPeriod = billingPeriod;
        return this;
    }

    public void setBillingPeriod(BillingPeriod billingPeriod) {
        this.billingPeriod = billingPeriod;
    }

    public Set<CustomerContact> getCustomerContacts() {
        return customerContacts;
    }

    public Customer customerContacts(Set<CustomerContact> customerContacts) {
        this.customerContacts = customerContacts;
        return this;
    }

    public Customer addCustomerContact(CustomerContact customerContact) {
        this.customerContacts.add(customerContact);
        customerContact.setCustomer(this);
        return this;
    }

    public Customer removeCustomerContact(CustomerContact customerContact) {
        this.customerContacts.remove(customerContact);
        customerContact.setCustomer(null);
        return this;
    }

    public void setCustomerContacts(Set<CustomerContact> customerContacts) {
        for(CustomerContact customerContact : customerContacts) {
            customerContact.setCustomer(this);
        }
        this.customerContacts = customerContacts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        return id != null && id.equals(((Customer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", clientId=" + getClientId() +
            ", name='" + getName() + "'" +
            ", company='" + getCompany() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
