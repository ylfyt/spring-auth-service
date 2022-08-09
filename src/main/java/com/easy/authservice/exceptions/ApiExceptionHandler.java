package com.easy.authservice.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.easy.authservice.dtos.ResponseDto;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(value = { ApiRequestException.class })
  public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
    var err = new ResponseDto<>(e.getStatus().name(), e.getMessage(), false, e.getStatus().value(), null);

    return new ResponseEntity<>(err, e.getStatus());
  }
}
