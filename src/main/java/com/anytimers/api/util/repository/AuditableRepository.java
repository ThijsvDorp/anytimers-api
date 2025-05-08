package com.anytimers.api.util.repository;

import java.time.Instant;
import java.util.List;

public interface AuditableRepository<T, ID> extends EntityRepository<T, ID> {

    List<T> findByCreatedOnAfter(Instant createdAfter);

    List<T> findByCreatedOnBefore(Instant createdBefore);

    List<T> findUpdatedOnById(ID id);

}
