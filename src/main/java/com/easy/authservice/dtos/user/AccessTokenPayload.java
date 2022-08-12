package com.easy.authservice.dtos.user;

public class AccessTokenPayload {
  public String username;

  public AccessTokenPayload(String username) {
    this.username = username;
  }
}
