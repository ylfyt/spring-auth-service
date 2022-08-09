package com.easy.authservice.dtos.user;

import com.easy.authservice.models.User;

public class DataUser {
  private User user;
  private String token = "";

  public DataUser(User user) {
    this.user = user;
  }

  public DataUser(User user, String token) {
    this.user = user;
    this.token = token;
  }

  public User getUser() {
    return user;
  }

  public String getToken() {
    return token;
  }
}
