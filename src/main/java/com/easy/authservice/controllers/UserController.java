package com.easy.authservice.controllers;

import com.easy.authservice.dtos.ResponseDto;
import com.easy.authservice.dtos.user.DataUser;
import com.easy.authservice.dtos.user.RegisterInputDto;
import com.easy.authservice.models.User;
import com.easy.authservice.services.IUserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

  private final IUserService userService;

  public UserController(IUserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<ResponseDto<Collection<User>>> getUsers() {
    return userService.getUsers();
  }

  @PostMapping("register")
  public ResponseEntity<ResponseDto<DataUser>> register(@Valid @RequestBody RegisterInputDto data) {
    return userService.register(data);
  }

  @PostMapping("login")
  public ResponseEntity<ResponseDto<DataUser>> login(@Valid @RequestBody RegisterInputDto data) {
    return userService.login(data);
  }
}
