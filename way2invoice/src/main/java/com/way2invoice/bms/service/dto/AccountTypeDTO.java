package com.way2invoice.bms.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.way2invoice.bms.domain.enumeration.Status;

/**
 * A DTO for the {@link com.way2invoice.bms.domain.AccountType} entity.
 */
public class AccountTypeDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        if (!(o instanceof AccountTypeDTO)) {
            return false;
        }

        return id != null && id.equals(((AccountTypeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AccountTypeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
