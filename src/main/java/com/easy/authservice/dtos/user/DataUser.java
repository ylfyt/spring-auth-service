package com.easy.authservice.dtos.user;

import com.easy.authservice.models.User;

public class DataUser {
  private User user;
  private TokenDto token;

  public DataUser(User user) {
    this.user = user;
  }

  public DataUser(User user, TokenDto tokenData) {
    this.user = user;
    this.token = tokenData;
  }

  public User getUser() {
    return user;
  }

  public TokenDto getToken() {
    return token;
  }
}
