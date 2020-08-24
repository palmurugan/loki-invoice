package com.way2invoice.bms.service.mapper;


import com.way2invoice.bms.domain.*;
import com.way2invoice.bms.service.dto.AccountTaxInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountTaxInfo} and its DTO {@link AccountTaxInfoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AccountTaxInfoMapper extends EntityMapper<AccountTaxInfoDTO, AccountTaxInfo> {



    default AccountTaxInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountTaxInfo accountTaxInfo = new AccountTaxInfo();
        accountTaxInfo.setId(id);
        return accountTaxInfo;
    }
}
