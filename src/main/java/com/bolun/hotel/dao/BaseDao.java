package com.bolun.hotel.dao;

import java.util.Optional;

public interface BaseDao<K, E> {

    E save(E entity);

    boolean update(E entity);

    Optional<E> findById(K id);

    boolean delete(K id);
}
