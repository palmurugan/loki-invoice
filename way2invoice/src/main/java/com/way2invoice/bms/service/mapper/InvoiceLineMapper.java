package com.way2invoice.bms.service.mapper;


import com.way2invoice.bms.domain.*;
import com.way2invoice.bms.service.dto.InvoiceLineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvoiceLine} and its DTO {@link InvoiceLineDTO}.
 */
@Mapper(componentModel = "spring", uses = {ItemMapper.class, InvoiceMapper.class})
public interface InvoiceLineMapper extends EntityMapper<InvoiceLineDTO, InvoiceLine> {

    @Mapping(source = "item.id", target = "itemId")
    @Mapping(source = "invoice.id", target = "invoiceId")
    InvoiceLineDTO toDto(InvoiceLine invoiceLine);

    @Mapping(source = "itemId", target = "item")
    @Mapping(source = "invoiceId", target = "invoice")
    InvoiceLine toEntity(InvoiceLineDTO invoiceLineDTO);

    default InvoiceLine fromId(Long id) {
        if (id == null) {
            return null;
        }
        InvoiceLine invoiceLine = new InvoiceLine();
        invoiceLine.setId(id);
        return invoiceLine;
    }
}
