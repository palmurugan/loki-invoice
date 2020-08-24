package com.way2invoice.bms.service.mapper;


import com.way2invoice.bms.domain.Payment;
import com.way2invoice.bms.service.dto.PaymentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Payment} and its DTO {@link PaymentDTO}.
 */
@Mapper(componentModel = "spring", uses = {AccountsMapper.class, PaymentMethodMapper.class})
public interface PaymentMapper extends EntityMapper<PaymentDTO, Payment> {

    @Mapping(source = "accountFrom.id", target = "accountFromId")
    @Mapping(source = "accountFrom.name", target = "accountFrom")
    @Mapping(source = "accountTo.id", target = "accountToId")
    @Mapping(source = "accountTo.name", target = "accountTo")
    @Mapping(source = "paymentMethod.id", target = "paymentMethodId")
    @Mapping(source = "paymentMethod.name", target = "paymentMethod")
    PaymentDTO toDto(Payment payment);

    @Mapping(source = "accountFromId", target = "accountFrom")
    @Mapping(source = "accountToId", target = "accountTo")
    @Mapping(source = "paymentMethodId", target = "paymentMethod")
    Payment toEntity(PaymentDTO paymentDTO);

    default Payment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Payment payment = new Payment();
        payment.setId(id);
        return payment;
    }
}
