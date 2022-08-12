package com.easy.authservice.services.TokenManager;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.easy.authservice.dtos.user.AccessTokenPayload;
import com.easy.authservice.models.RefreshToken;
import com.easy.authservice.models.User;
import com.easy.authservice.repositories.RefreshTokenRepository;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenManager implements ITokenManager {

  public static final long JWT_REFRESH_TOKEN_EXPIRY_TIME = 24 * 60 * 60; // 1 Day
  public static final long JWT_ACCESS_TOKEN_EXPIRY_TIME = 60 * 60; // 10 Minutes

  @Value("${jwt.REFRESH_TOKEN_SECRET_KEY}")
  private String refreshSecret;
  @Value("${jwt.ACCESS_TOKEN_SECRET_KEY}")
  private String accessSecret;

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
        .signWith(SignatureAlgorithm.HS512, refreshSecret).compact();

    var newRefreshToken = new RefreshToken();
    newRefreshToken.token = temp;
    newRefreshToken.userId = user.getId();
    refreshTokenRepository.save(newRefreshToken);

    token.append(temp);

    return newRefreshToken.getId();
  }

  @Override
  public String createAccessToken(User user, Long refreshTokenId) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("username", user.getUsername());
    claims.put("jid", refreshTokenId);

    String token = Jwts.builder().setClaims(claims)
        .setExpiration(new Date(System.currentTimeMillis() + JWT_ACCESS_TOKEN_EXPIRY_TIME * 1000))
        .signWith(SignatureAlgorithm.HS512, accessSecret).compact();
    return token;
  }

  @Override
  public String verifyRefreshToken(String token) {

    try {
      Jwts.parser().setSigningKey(refreshSecret).parseClaimsJws(token);

      return null;
    } catch (ExpiredJwtException ex) {
      return "TOKEN_EXPIRED";
    } catch (Exception e) {
      return "NOT_VALID";
    }
  }

  @Override
  public AccessTokenPayload verifyAccessToken(String token) {
    try {

      Map<String, Object> claims = Jwts.parser().setSigningKey(accessSecret).parseClaimsJws(token).getBody();

      String username = (String) claims.get("username");
      Long jid = Long.valueOf((int) claims.get("jid"));

      if (username == null || jid == null)
        return null;

      // TODO: Check Blacklist

      return new AccessTokenPayload(username);
    } catch (Exception e) {

      System.out.println(e.getMessage());
      return null;
    }
  }
}
