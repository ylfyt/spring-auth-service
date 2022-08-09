package com.easy.authservice.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(value = { ApiRequestException.class })
  public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
    var err = new ApiExceptionDto(e.getMessage(), e.getStatus(), ZonedDateTime.now(ZoneId.of("Z")));

    return new ResponseEntity<>(err, e.getStatus());
  }
}
