package com.runner.service;


public interface ManagementService<T> {
    /**
     * Create entity in database
     *
     * @param entity entity that should be created
     * @return id of entity that is created in database
     */
    Long create(T entity) ;

    /**
     * Read entity from database
     *
     * @param id of entity that should be read
     * @return Entity from database by id;

     */
    T get(Long id) ;

    /**
     * Update entity in database
     *
     * @param entity Entity that should be updated

     */
    void update(T entity) ;

    /**
     * Delete entity from database
     *
     * @param id of entity that should be deleted

     */
    boolean delete(Long id) ;
}

