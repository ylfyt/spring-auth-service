package com.easy.authservice.services.TokenManager;

import com.easy.authservice.dtos.user.AccessTokenPayload;
import com.easy.authservice.models.User;

public interface ITokenManager {
  public Long createRefreshToken(User user, StringBuilder token);

  public String createAccessToken(User user, Long refreshTokenId);

  public String verifyRefreshToken(String token);

  public AccessTokenPayload verifyAccessToken(String token);
}
