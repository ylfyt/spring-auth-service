package com.easy.authservice.dtos;

public class ResponseDto<T> {
  public String error = "";
  public String message = "";
  public boolean success = true;
  public int status = 200;
  public T data;

  public ResponseDto(T data) {
    this.data = data;
  }

  public ResponseDto(String error, String message, boolean success, int status, T data) {
    this.error = error;
    this.message = message;
    this.success = success;
    this.status = status;
    this.data = data;
  }
}
