package com.easy.authservice.controllers;

import com.easy.authservice.dtos.ResponseDto;
import com.easy.authservice.dtos.user.AccessTokenDto;
import com.easy.authservice.dtos.user.DataUser;
import com.easy.authservice.dtos.user.RefreshTokenDto;
import com.easy.authservice.dtos.user.RegisterInputDto;
import com.easy.authservice.dtos.user.TokenDto;
import com.easy.authservice.dtos.user.VerifyAccessTokenDto;
import com.easy.authservice.models.User;
import com.easy.authservice.services.IUserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

  private final IUserService userService;

  public AuthController(IUserService userService) {
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

  @PostMapping("verify-access-token")
  public ResponseEntity<ResponseDto<VerifyAccessTokenDto>> verifyAccessToken(@Valid @RequestBody AccessTokenDto data) {
    return userService.verifyAccessToken(data);
  }

  @PostMapping("refresh-token")
  public ResponseEntity<ResponseDto<TokenDto>> refreshToken(@Valid @RequestBody RefreshTokenDto data) {
    return userService.refreshToken(data);
  }

  @PostMapping("logout")
  public ResponseEntity<ResponseDto<Boolean>> logout(@Valid @RequestBody RefreshTokenDto data) {
    return userService.logout(data);
  }
}
