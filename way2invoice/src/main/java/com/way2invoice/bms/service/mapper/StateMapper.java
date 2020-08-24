package com.way2invoice.bms.service.mapper;

import com.way2invoice.bms.domain.State;
import com.way2invoice.bms.service.dto.StateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StateMapper extends EntityMapper<StateDTO, State> {

    default State fromId(Long id) {
        if (id == null) {
            return null;
        }
        State state = new State();
        state.setId(id);
        return state;
    }
}
