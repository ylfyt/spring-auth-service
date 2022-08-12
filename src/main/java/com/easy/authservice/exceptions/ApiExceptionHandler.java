package com.easy.authservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.easy.authservice.dtos.ResponseDto;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(value = { Exception.class })
  public ResponseEntity<Object> handleApiRequestException(Exception e) {
    if (e instanceof ApiRequestException) {
      var newE = (ApiRequestException) e;
      var err = new ResponseDto<>(newE.getStatus().name(), newE.getMessage(), false, newE.getStatus().value(), null);
      return new ResponseEntity<>(err, newE.getStatus());
    }

    if (e instanceof MethodArgumentNotValidException) {
      var status = HttpStatus.BAD_REQUEST;
      var newE = (MethodArgumentNotValidException) e;
      var err = new ResponseDto<>(status.name(),
          newE.getFieldError().getField() + " " + newE.getFieldError().getDefaultMessage(), false,
          status.value(), null);
      return new ResponseEntity<>(err, status);
    }

    // LOGGING
    System.out.println("[SERVER ERROR] " + e.getMessage());

    return new ResponseEntity<>(new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.name(), "Internal Server Error",
        false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
