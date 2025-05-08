package com.anytimers.api.domain.user.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.anytimers.api.util.repository.AuditableRepository;
/**
 * 
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>, AuditableRepository<User, Integer> {
    
    Optional<User> findByEmail(String email);

    List<User> findAllByRole(Role role);

    boolean existsByEmail(String email);
    
    Optional<User> findByUserNameLike(String userName);

    boolean existsByUserName(String userName);

    Optional<User> findByUserNameOrEmail(String userName, String email);

}
