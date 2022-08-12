package com.easy.authservice.models;

import javax.persistence.Entity;

@Entity
public class RefreshToken extends BaseModel {
  public Long userId;
  public String token;
}
