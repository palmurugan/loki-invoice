package com.way2invoice.bms.service.mapper;


import com.way2invoice.bms.domain.InvoicePayment;
import com.way2invoice.bms.service.dto.InvoicePaymentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link InvoicePayment} and its DTO {@link InvoicePaymentDTO}.
 */
@Mapper(componentModel = "spring", uses = {PaymentMapper.class, InvoiceMapper.class})
public interface InvoicePaymentMapper extends EntityMapper<InvoicePaymentDTO, InvoicePayment> {

    @Mapping(source = "invoice.name", target = "invoiceName")
    @Mapping(source = "invoice.id", target = "invoiceId")
    InvoicePaymentDTO toDto(InvoicePayment invoicePayment);

    @Mapping(source = "invoiceId", target = "invoice")
    InvoicePayment toEntity(InvoicePaymentDTO invoicePaymentDTO);

    default InvoicePayment fromId(Long id) {
        if (id == null) {
            return null;
        }
        InvoicePayment invoicePayment = new InvoicePayment();
        invoicePayment.setId(id);
        return invoicePayment;
    }
}
