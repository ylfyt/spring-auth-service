package com.easy.authservice.exceptions;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class ApiExceptionDto {
  private final String error;
  private final String message;
  private final int status;
  private final ZonedDateTime timeStamp;

  public ApiExceptionDto(String message, HttpStatus status, ZonedDateTime timeStamp) {
    this.message = message;
    this.timeStamp = timeStamp;
    this.error = status.name();
    this.status = status.value();
  }

  public String getError() {
    return error;
  }

  public String getMessage() {
    return message;
  }

  public int getStatus() {
    return status;
  }

  public ZonedDateTime getTimeStamp() {
    return timeStamp;
  }
}
