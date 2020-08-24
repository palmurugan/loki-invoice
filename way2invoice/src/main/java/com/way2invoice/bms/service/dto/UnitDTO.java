package com.way2invoice.bms.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.way2invoice.bms.domain.enumeration.Status;

/**
 * A DTO for the {@link com.way2invoice.bms.domain.Unit} entity.
 */
public class UnitDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long clientId;

    @NotNull
    @Size(max = 12)
    private String name;

    @NotNull
    @Size(max = 12)
    private String code;

    @NotNull
    private Status status;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
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
        if (!(o instanceof UnitDTO)) {
            return false;
        }

        return id != null && id.equals(((UnitDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UnitDTO{" +
            "id=" + getId() +
            ", clientId=" + getClientId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
