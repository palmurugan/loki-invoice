package com.way2invoice.bms.service.mapper;


import com.way2invoice.bms.domain.AccountContact;
import com.way2invoice.bms.service.dto.AccountContactDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link AccountContact} and its DTO {@link AccountContactDTO}.
 */
@Mapper(componentModel = "spring", uses = {AccountsMapper.class, StateMapper.class,
    CountryMapper.class})
public interface AccountContactMapper extends EntityMapper<AccountContactDTO, AccountContact> {

    @Mapping(source = "accounts.id", target = "accountsId")
    @Mapping(source = "state.id", target = "stateId")
    @Mapping(source = "country.id", target = "countryId")
    AccountContactDTO toDto(AccountContact accountContact);

    @Mapping(source = "accountsId", target = "accounts")
    @Mapping(source = "stateId", target = "state")
    @Mapping(source = "countryId", target = "country")
    AccountContact toEntity(AccountContactDTO accountContactDTO);

    default AccountContact fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountContact accountContact = new AccountContact();
        accountContact.setId(id);
        return accountContact;
    }
}
