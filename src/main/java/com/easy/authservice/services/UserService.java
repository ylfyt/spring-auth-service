package com.easy.authservice.services;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.easy.authservice.dtos.ResponseDto;
import com.easy.authservice.dtos.user.AccessTokenDto;
import com.easy.authservice.dtos.user.DataUser;
import com.easy.authservice.dtos.user.RegisterInputDto;
import com.easy.authservice.dtos.user.TokenDto;
import com.easy.authservice.dtos.user.VerifyAccessTokenDto;
import com.easy.authservice.exceptions.ApiRequestException;
import com.easy.authservice.models.User;
import com.easy.authservice.repositories.UserRepository;
import com.easy.authservice.services.TokenManager.ITokenManager;

@Service
public class UserService implements IUserService {

  private UserRepository userRepository;
  private ITokenManager tokenManager;

  public UserService(UserRepository userRepository, ITokenManager tokenManager) {
    this.userRepository = userRepository;
    this.tokenManager = tokenManager;
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

  @Override
  public ResponseEntity<ResponseDto<DataUser>> login(RegisterInputDto data) {
    var user = userRepository.findByUsername(data.username);
    if (user == null)
      throw new ApiRequestException(HttpStatus.BAD_REQUEST, "Username or Password is Wrong");

    var bCryptPasswordEncoder = new BCryptPasswordEncoder();
    if (!bCryptPasswordEncoder.matches(data.password, user.getPassword()))
      throw new ApiRequestException(HttpStatus.BAD_REQUEST, "Username or Password is Wrong");

    StringBuilder refreshToken = new StringBuilder();
    Long refreshTokenId = tokenManager.createRefreshToken(user, refreshToken);
    if (refreshTokenId == null || refreshToken.toString().isEmpty())
      throw new ApiRequestException(HttpStatus.BAD_REQUEST, "Already logged in with other devices");

    String accessToken = tokenManager.createAccessToken(user, refreshTokenId);

    return ResponseEntity
        .ok(new ResponseDto<>(new DataUser(user, new TokenDto(accessToken, refreshToken.toString()))));
  }

  @Override
  public ResponseEntity<ResponseDto<VerifyAccessTokenDto>> verifyAccessToken(AccessTokenDto data) {

    var payload = tokenManager.verifyAccessToken(data.token);

    return ResponseEntity.ok(
        new ResponseDto<>(
            new VerifyAccessTokenDto(payload != null, payload)));
  }
}
