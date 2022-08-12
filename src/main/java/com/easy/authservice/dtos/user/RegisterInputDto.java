package com.easy.authservice.dtos.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterInputDto {
  @NotNull
  @Size(min = 4, max = 20)
  public String username;
  @NotNull
  @Size(min = 4, max = 20)
  public String password;
}
