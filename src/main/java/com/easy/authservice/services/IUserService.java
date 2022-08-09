package com.easy.authservice.services;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import com.easy.authservice.dtos.ResponseDto;
import com.easy.authservice.dtos.user.DataUser;
import com.easy.authservice.dtos.user.RegisterInputDto;
import com.easy.authservice.models.User;

public interface IUserService {
  public ResponseEntity<ResponseDto<Collection<User>>> getUsers();

  public ResponseEntity<ResponseDto<DataUser>> register(RegisterInputDto data);
}
