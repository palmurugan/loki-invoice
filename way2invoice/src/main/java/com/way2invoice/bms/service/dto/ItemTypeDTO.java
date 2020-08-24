package com.way2invoice.bms.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.way2invoice.bms.domain.enumeration.Status;

/**
 * A DTO for the {@link com.way2invoice.bms.domain.ItemType} entity.
 */
public class ItemTypeDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long clientId;

    @NotNull
    private String name;

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
        if (!(o instanceof ItemTypeDTO)) {
            return false;
        }

        return id != null && id.equals(((ItemTypeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemTypeDTO{" +
            "id=" + getId() +
            ", clientId=" + getClientId() +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
