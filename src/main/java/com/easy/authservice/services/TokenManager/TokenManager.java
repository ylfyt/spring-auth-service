package com.easy.authservice.services.TokenManager;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.easy.authservice.models.RefreshToken;
import com.easy.authservice.models.User;
import com.easy.authservice.repositories.RefreshTokenRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenManager implements ITokenManager {

  public static final long JWT_REFRESH_TOKEN_EXPIRY_TIME = 24 * 60 * 60;

  @Value("${jwt.secret}")
  private String secret;

  private final RefreshTokenRepository refreshTokenRepository;

  public TokenManager(RefreshTokenRepository refreshTokenRepository) {
    this.refreshTokenRepository = refreshTokenRepository;
  }

  @Override
  public Long createRefreshToken(User user, StringBuilder token) {
    var tokens = refreshTokenRepository.findByUserId(user.getId());
    if (tokens.size() >= 2) {
      return null;
    }

    String temp = Jwts.builder()
        .setExpiration(new Date(System.currentTimeMillis() + JWT_REFRESH_TOKEN_EXPIRY_TIME * 1000))
        .signWith(SignatureAlgorithm.HS512, secret).compact();

    var newRefreshToken = new RefreshToken();
    newRefreshToken.token = temp;
    newRefreshToken.userId = user.getId();
    refreshTokenRepository.save(newRefreshToken);

    token.append(temp);

    return newRefreshToken.getId();
  }

}
