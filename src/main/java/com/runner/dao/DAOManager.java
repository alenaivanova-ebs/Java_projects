package com.runner.dao;



public interface DAOManager<T> {
    T get(Long id);
    Long create(T entity);

}

