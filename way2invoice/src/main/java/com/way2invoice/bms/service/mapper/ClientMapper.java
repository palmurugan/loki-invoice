package com.way2invoice.bms.service.mapper;


import com.way2invoice.bms.domain.*;
import com.way2invoice.bms.service.dto.ClientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Client} and its DTO {@link ClientDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {



    default Client fromId(Long id) {
        if (id == null) {
            return null;
        }
        Client client = new Client();
        client.setId(id);
        return client;
    }
}
