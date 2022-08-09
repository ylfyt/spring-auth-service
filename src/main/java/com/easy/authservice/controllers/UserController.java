package com.easy.authservice.controllers;

import com.easy.authservice.dtos.ResponseDto;
import com.easy.authservice.dtos.user.DataUser;
import com.easy.authservice.dtos.user.RegisterInputDto;
import com.easy.authservice.exceptions.ApiRequestException;
import com.easy.authservice.models.User;
import com.easy.authservice.repositories.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.*;

import java.util.Collection;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping
  public ResponseEntity<ResponseDto<Collection<User>>> getUsers() {
    var data = userRepository.findAll();
    return ResponseEntity.ok(new ResponseDto<>(data));
  }

  @PostMapping("register")
  public ResponseEntity<ResponseDto<DataUser>> register(@Valid @RequestBody RegisterInputDto data) {

    var exist = userRepository.findByUsername(data.username);
    if (exist != null)
      throw new ApiRequestException(HttpStatus.BAD_REQUEST, "Username Already Exist");

    var bCryptPasswordEncoder = new BCryptPasswordEncoder();
    var hashedPassword = bCryptPasswordEncoder.encode(data.password);

    var user = new User(data.username, hashedPassword);
    userRepository.save(user);

    return ResponseEntity.ok(new ResponseDto<>(new DataUser(user)));
  }
}
