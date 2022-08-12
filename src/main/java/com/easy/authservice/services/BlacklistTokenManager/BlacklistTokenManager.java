package com.easy.authservice.services.BlacklistTokenManager;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.easy.authservice.services.TokenManager.TokenManager;

@Service
public class BlacklistTokenManager implements IBlacklistTokenManger {
  public static ArrayList<Long> refreshTokenIds = new ArrayList<>();

  public boolean createToken(Long refreshTokenId) {
    refreshTokenIds.add(refreshTokenId);
    new java.util.Timer().schedule(
        new java.util.TimerTask() {
          @Override
          public void run() {
            System.out.println("Delete token " + refreshTokenId);
            refreshTokenIds.remove(refreshTokenId);
          }
        },
        TokenManager.JWT_ACCESS_TOKEN_EXPIRY_TIME * 1000);
    return true;
  }

  public boolean isRefreshTokenIdExist(Long refreshTokenId) {
    for (int i = 0; i < refreshTokenIds.size(); i++) {
      if (refreshTokenIds.get(i) == refreshTokenId)
        return true;
    }
    return false;
  }
}
