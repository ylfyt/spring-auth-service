package com.easy.authservice.dtos.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccessTokenDto {
  @NotNull
  @NotEmpty
  public String token;
}
