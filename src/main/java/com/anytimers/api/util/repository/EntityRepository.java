package com.anytimers.api.util.repository;

import java.util.Optional;

public interface EntityRepository<T, ID> {
    
    Optional<T> findById(ID id);
    
}
