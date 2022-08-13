package com.easy.authservice.controllers;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @Autowired
  private Environment env;

  @GetMapping("/")
  public String hello() {
    return env.toString();
  }
}
