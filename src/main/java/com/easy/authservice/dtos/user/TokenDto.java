package com.easy.authservice.dtos.user;

public class TokenDto {
  public String access;
  public String refresh;

  public TokenDto(String access, String refresh) {
    this.access = access;
    this.refresh = refresh;
  }
}
