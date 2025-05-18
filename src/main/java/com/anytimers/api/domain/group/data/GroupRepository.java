package com.anytimers.api.domain.group.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.anytimers.api.util.repository.AuditableRepository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer>, JpaSpecificationExecutor<Group>, AuditableRepository<Group, Integer> {

    Optional<Group> findByGroupName(String name);

    Optional<Group> findByOwnerId(int ownerId);

}
