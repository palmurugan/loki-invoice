package com.way2invoice.bms.common.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * A Generic service for all basic implementation
 */
public interface GenericService<E, K> {

    E save(E entity);

    Page<E> findAll(Pageable pageable);

    Optional<E> findById(K id);
}
