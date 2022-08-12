package com.easy.authservice.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easy.authservice.models.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  public Collection<RefreshToken> findByUserId(Long userId);

  public RefreshToken findByToken(String token);
}
