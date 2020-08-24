package com.way2invoice.bms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.way2invoice.bms.common.auditing.domain.Auditable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.way2invoice.bms.domain.enumeration.Status;

/**
 * A Accounts.
 */
@Entity
@Table(name = "accounts")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Accounts extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "opening_balance", precision = 21, scale = 2)
    private BigDecimal openingBalance;

    @OneToOne
    @JoinColumn(unique = true)
    private AccountTaxInfo accountTaxInfo;

    @OneToMany(mappedBy = "accounts", cascade = CascadeType.ALL)
    private Set<AccountContact> accountContacts = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "accounts", allowSetters = true)
    private AccountType accountType;

    @ManyToOne
    @JsonIgnoreProperties(value = "accounts", allowSetters = true)
    private AccountGroup accountGroup;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Accounts name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getOpeningBalance() {
        return openingBalance;
    }

    public Accounts openingBalance(BigDecimal openingBalance) {
        this.openingBalance = openingBalance;
        return this;
    }

    public void setOpeningBalance(BigDecimal openingBalance) {
        this.openingBalance = openingBalance;
    }

    public AccountTaxInfo getAccountTaxInfo() {
        return accountTaxInfo;
    }

    public Accounts accountTaxInfo(AccountTaxInfo accountTaxInfo) {
        this.accountTaxInfo = accountTaxInfo;
        return this;
    }

    public void setAccountTaxInfo(AccountTaxInfo accountTaxInfo) {
        this.accountTaxInfo = accountTaxInfo;
    }

    public Set<AccountContact> getAccountContacts() {
        return accountContacts;
    }

    public Accounts accountContacts(Set<AccountContact> accountContacts) {
        for(AccountContact accountContact: accountContacts) {
            accountContact.setAccounts(this);
        }
        this.accountContacts = accountContacts;
        return this;
    }

    public Accounts addAccountContact(AccountContact accountContact) {
        this.accountContacts.add(accountContact);
        accountContact.setAccounts(this);
        return this;
    }

    public Accounts removeAccountContact(AccountContact accountContact) {
        this.accountContacts.remove(accountContact);
        accountContact.setAccounts(null);
        return this;
    }

    public void setAccountContacts(Set<AccountContact> accountContacts) {
        for(AccountContact accountContact: accountContacts) {
            accountContact.setAccounts(this);
        }
        this.accountContacts = accountContacts;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Accounts accountType(AccountType accountType) {
        this.accountType = accountType;
        return this;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountGroup getAccountGroup() {
        return accountGroup;
    }

    public Accounts accountGroup(AccountGroup accountGroup) {
        this.accountGroup = accountGroup;
        return this;
    }

    public void setAccountGroup(AccountGroup accountGroup) {
        this.accountGroup = accountGroup;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Accounts)) {
            return false;
        }
        return id != null && id.equals(((Accounts) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Accounts{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", openingBalance=" + getOpeningBalance() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
