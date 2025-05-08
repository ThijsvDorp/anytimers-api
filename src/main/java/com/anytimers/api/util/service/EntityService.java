package com.anytimers.api.util.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.anytimers.api.util.repository.EntityRepository;

/**
 * Abstract service that provides functionality for the service layer.
 * @param <T> The Entity class 
 * @param <U> The Repository that extends JpaRepository, SpaSpecificationExecutor and EntityRepository
 */
public abstract class EntityService<T, U extends JpaRepository<T, Integer> & JpaSpecificationExecutor<T> & EntityRepository<T, Integer>> {
    protected final String ENTITY_NAME;

    protected final U repository;

    public EntityService(String entityName, U repository) {
        this.ENTITY_NAME = entityName;
        this.repository = repository;
    }
}
