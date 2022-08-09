package com.easy.authservice.exceptions;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException {

  private HttpStatus status;

  public ApiRequestException(HttpStatus status, String message) {
    super(message);
    this.status = status;
  }

  public ApiRequestException(HttpStatus status, String message, Throwable e) {
    super(message, e);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }
}
