package com.way2invoice.bms.service.dto;

import com.way2invoice.bms.domain.enumeration.Status;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link com.way2invoice.bms.domain.CustomerContact} entity.
 */
public class CustomerContactDTO implements Serializable {

  private Long id;

  @NotNull
  private String address1;

  private String address2;

  @NotNull
  @Size(max = 32)
  private String city;

  @NotNull
  @Size(max = 15)
  private String state;

  @NotNull
  @Size(max = 15)
  private String country;

  @NotNull
  @Size(max = 12)
  private String zipcode;

  private Status status;

  private Long customerId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    if (status == null) {
      this.status = Status.ACTIVE;
    } else {
      this.status = status;
    }
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CustomerContactDTO)) {
      return false;
    }

    return id != null && id.equals(((CustomerContactDTO) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  // prettier-ignore
  @Override
  public String toString() {
    return "CustomerContactDTO{" +
        "id=" + getId() +
        ", address1='" + getAddress1() + "'" +
        ", address2='" + getAddress2() + "'" +
        ", city='" + getCity() + "'" +
        ", state='" + getState() + "'" +
        ", country='" + getCountry() + "'" +
        ", zipcode='" + getZipcode() + "'" +
        ", status='" + getStatus() + "'" +
        ", customerId=" + getCustomerId() +
        "}";
  }
}
