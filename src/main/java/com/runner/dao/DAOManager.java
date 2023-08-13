package com.runner.dao;


import java.util.Optional;

public interface DAOManager<T> {
    Optional<T> get(Long id);
    Long create(T entity);

}

