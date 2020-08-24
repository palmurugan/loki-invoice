package com.way2invoice.bms.service.dto;

import com.way2invoice.bms.domain.enumeration.Status;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link com.way2invoice.bms.domain.Payment} entity.
 */
public class PaymentDTO implements Serializable {

    private Long id;

    @NotNull(message = "From account is required")
    private Long accountFromId;

    private String accountFrom;

    @NotNull(message = "To account is required")
    private Long accountToId;

    private String accountTo;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private LocalDate date;

    private Status status;

    private Long paymentMethodId;

    private String paymentMethod;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getAccountFromId() {
        return accountFromId;
    }

    public void setAccountFromId(Long accountFromId) {
        this.accountFromId = accountFromId;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Long getAccountToId() {
        return accountToId;
    }

    public void setAccountToId(Long accountToId) {
        this.accountToId = accountToId;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentDTO)) {
            return false;
        }

        return id != null && id.equals(((PaymentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "PaymentDTO{" +
            "id=" + id +
            ", accountFromId=" + accountFromId +
            ", accountFrom='" + accountFrom + '\'' +
            ", accountToId=" + accountToId +
            ", accountTo='" + accountTo + '\'' +
            ", amount=" + amount +
            ", date=" + date +
            ", status=" + status +
            ", paymentMethodId=" + paymentMethodId +
            '}';
    }
}
