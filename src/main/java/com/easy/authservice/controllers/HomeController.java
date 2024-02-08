package com.easy.authservice.controllers;

import com.easy.authservice.middlewares.Authorization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @GetMapping("/")
  public String hello() {
    return "Hello, World!";
  }

  @GetMapping("ping")
  @Authorization
  public String ping() {
    return "pong";
  }
}
