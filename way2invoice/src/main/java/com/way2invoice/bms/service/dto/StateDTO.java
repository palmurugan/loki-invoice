package com.way2invoice.bms.service.dto;

import com.way2invoice.bms.domain.enumeration.Status;
import javax.validation.constraints.NotNull;

public class StateDTO {

    private Long id;

    @NotNull
    private Long countryId;

    @NotNull
    private String code;

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

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
