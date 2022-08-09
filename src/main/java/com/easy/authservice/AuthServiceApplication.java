package com.easy.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@RestController
public class AuthServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthServiceApplication.class, args);
  }

  @GetMapping("/")
  public static String hello() {
    return "Hello";
  }
}
