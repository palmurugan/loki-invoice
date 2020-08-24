package com.way2invoice.bms.service.mapper;


import com.way2invoice.bms.domain.*;
import com.way2invoice.bms.service.dto.AccountGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountGroup} and its DTO {@link AccountGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AccountGroupMapper extends EntityMapper<AccountGroupDTO, AccountGroup> {



    default AccountGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountGroup accountGroup = new AccountGroup();
        accountGroup.setId(id);
        return accountGroup;
    }
}
