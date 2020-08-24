package com.way2invoice.bms.service.mapper;


import com.way2invoice.bms.domain.Invoice;
import com.way2invoice.bms.service.dto.InvoiceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Invoice} and its DTO {@link InvoiceDTO}.
 */
@Mapper(componentModel = "spring", uses = {AccountsMapper.class, InvoiceLineMapper.class})
public interface InvoiceMapper extends EntityMapper<InvoiceDTO, Invoice> {

  @Mapping(source = "account.name", target = "accountName")
  @Mapping(source = "account.id", target = "accountId")
  InvoiceDTO toDto(Invoice invoice);

  @Mapping(source = "accountId", target = "account")
  Invoice toEntity(InvoiceDTO invoiceDTO);

  default Invoice fromId(Long id) {
    if (id == null) {
      return null;
    }
    Invoice invoice = new Invoice();
    invoice.setId(id);
    return invoice;
  }
}
