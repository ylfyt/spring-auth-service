package com.easy.authservice.services;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.easy.authservice.dtos.ResponseDto;
import com.easy.authservice.dtos.user.DataUser;
import com.easy.authservice.dtos.user.RegisterInputDto;
import com.easy.authservice.exceptions.ApiRequestException;
import com.easy.authservice.models.User;
import com.easy.authservice.repositories.UserRepository;

@Service
public class UserService implements IUserService {

  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public ResponseEntity<ResponseDto<DataUser>> register(RegisterInputDto data) {
    var exist = userRepository.findByUsername(data.username);
    if (exist != null)
      throw new ApiRequestException(HttpStatus.BAD_REQUEST, "Username Already Exist");

    var bCryptPasswordEncoder = new BCryptPasswordEncoder();
    var hashedPassword = bCryptPasswordEncoder.encode(data.password);

    var user = new User(data.username, hashedPassword);
    userRepository.save(user);

    return ResponseEntity.ok(new ResponseDto<>(new DataUser(user)));
  }

  @Override
  public ResponseEntity<ResponseDto<Collection<User>>> getUsers() {
    var data = userRepository.findAll();
    return ResponseEntity.ok(new ResponseDto<>(data));
  }
}
