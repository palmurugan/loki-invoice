package com.way2invoice.bms.service.dto;

import com.way2invoice.bms.domain.enumeration.Status;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link com.way2invoice.bms.domain.InvoicePayment} entity.
 */
public class InvoicePaymentDTO implements Serializable {

    private Long id;

    @NotNull
    private Long invoiceId;

    private String invoiceName;

    private Status status;

    @Valid
    private PaymentDTO payment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvoicePaymentDTO)) {
            return false;
        }

        return id != null && id.equals(((InvoicePaymentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvoicePaymentDTO{" +
            "id=" + id +
            ", invoiceId=" + invoiceId +
            ", status=" + status +
            ", payment=" + payment +
            '}';
    }
}
