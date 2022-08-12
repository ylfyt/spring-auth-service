package com.easy.authservice.dtos.user;

public class VerifyAccessTokenDto {
  public boolean valid;
  public AccessTokenPayload payload;

  public VerifyAccessTokenDto(boolean valid, AccessTokenPayload payload) {
    this.valid = valid;
    this.payload = payload;
  }
}
