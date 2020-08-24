package com.way2invoice.bms.service.mapper;


import com.way2invoice.bms.domain.Accounts;
import com.way2invoice.bms.service.dto.AccountsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Accounts} and its DTO {@link AccountsDTO}.
 */
@Mapper(componentModel = "spring", uses = {AccountTaxInfoMapper.class, AccountTypeMapper.class,
    AccountGroupMapper.class, AccountContactMapper.class})
public interface AccountsMapper extends EntityMapper<AccountsDTO, Accounts> {

    @Mapping(source = "accountTaxInfo.id", target = "accountTaxInfoId")
    @Mapping(source = "accountType.id", target = "accountTypeId")
    @Mapping(source = "accountType.name", target = "accountType")
    @Mapping(source = "accountGroup.id", target = "accountGroupId")
    @Mapping(source = "accountGroup.name", target = "accountGroup")
    AccountsDTO toDto(Accounts accounts);

    @Mapping(source = "accountTaxInfoId", target = "accountTaxInfo")
    @Mapping(source = "accountTypeId", target = "accountType")
    @Mapping(source = "accountGroupId", target = "accountGroup")
    Accounts toEntity(AccountsDTO accountsDTO);

    default Accounts fromId(Long id) {
        if (id == null) {
            return null;
        }
        Accounts accounts = new Accounts();
        accounts.setId(id);
        return accounts;
    }
}
