package com.easy.authservice.dtos.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterInputDto {
    @NotNull
    @Size(min = 4)
    public String username;
    @NotNull
    @Size(min = 4)
    public String password;
}
