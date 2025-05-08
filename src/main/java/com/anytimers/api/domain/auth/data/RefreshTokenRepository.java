package com.anytimers.api.domain.auth.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    
    Optional<RefreshToken> findByToken(String token);

    void deleteByUserId(Integer userId);

    void deleteByExpiresAtBefore(Instant now);
}
