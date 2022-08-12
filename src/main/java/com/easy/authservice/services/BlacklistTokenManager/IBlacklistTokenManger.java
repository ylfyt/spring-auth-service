package com.easy.authservice.services.BlacklistTokenManager;

public interface IBlacklistTokenManger {
  public boolean createToken(Long refreshTokenId);

  public boolean isRefreshTokenIdExist(Long refreshTokenId);
}
