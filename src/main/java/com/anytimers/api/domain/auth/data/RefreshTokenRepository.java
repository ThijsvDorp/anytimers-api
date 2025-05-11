package com.anytimers.api.domain.auth.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.anytimers.api.util.repository.EntityRepository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer>, JpaSpecificationExecutor<RefreshToken>, EntityRepository<RefreshToken, Integer> {
    
    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUserIdAndExpiresAtAfter(Integer userId, Instant now);

    //void deleteByUserId(Integer userId);

    void deleteByExpiresAtBefore(Instant now);

    void deleteByToken(String token);
}
