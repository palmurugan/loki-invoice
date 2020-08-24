package com.way2invoice.bms.common.service.impl;

import com.way2invoice.bms.common.service.GenericService;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * A Generic service implementation with basic functions
 *
 * @param <E>
 * @param <K>
 */
public class GenericServiceImpl<E, K> implements GenericService<E, K> {

    private final PagingAndSortingRepository<E, K> repository;

    public GenericServiceImpl(
        PagingAndSortingRepository<E, K> repository) {
        this.repository = repository;
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<E> findById(K id) {
        return repository.findById(id);
    }
}
