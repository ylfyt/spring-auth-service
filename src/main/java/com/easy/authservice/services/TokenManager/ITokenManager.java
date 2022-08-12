package com.easy.authservice.services.TokenManager;

import com.easy.authservice.models.User;

public interface ITokenManager {
  public Long createRefreshToken(User user, StringBuilder token);
}
