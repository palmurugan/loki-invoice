package com.way2invoice.bms.service.dto;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import com.way2invoice.bms.domain.enumeration.Status;

/**
 * A DTO for the {@link com.way2invoice.bms.domain.Accounts} entity.
 */
public class AccountsDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private BigDecimal openingBalance;

    private Status status;

    private Long accountTaxInfoId;

    @NotNull(message = "Account type is required")
    private Long accountTypeId;

    private String accountType;

    private Long accountGroupId;

    private String accountGroup;

    private Set<AccountContactDTO> accountContacts = new HashSet<>();

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

    public BigDecimal getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(BigDecimal openingBalance) {
        this.openingBalance = openingBalance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getAccountTaxInfoId() {
        return accountTaxInfoId;
    }

    public void setAccountTaxInfoId(Long accountTaxInfoId) {
        this.accountTaxInfoId = accountTaxInfoId;
    }

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public Long getAccountGroupId() {
        return accountGroupId;
    }

    public void setAccountGroupId(Long accountGroupId) {
        this.accountGroupId = accountGroupId;
    }

    public String getAccountGroup() {
        return accountGroup;
    }

    public void setAccountGroup(String accountGroup) {
        this.accountGroup = accountGroup;
    }

    public Set<AccountContactDTO> getAccountContacts() {
        return accountContacts;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setAccountContacts(
        Set<AccountContactDTO> accountContacts) {
        this.accountContacts = accountContacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountsDTO)) {
            return false;
        }

        return id != null && id.equals(((AccountsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AccountsDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", openingBalance=" + getOpeningBalance() +
            ", status='" + getStatus() + "'" +
            ", accountTaxInfoId=" + getAccountTaxInfoId() +
            ", accountTypeId=" + getAccountTypeId() +
            ", accountGroupId=" + getAccountGroupId() +
            "}";
    }
}
